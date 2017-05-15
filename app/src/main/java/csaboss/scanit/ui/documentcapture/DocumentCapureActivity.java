package csaboss.scanit.ui.documentcapture;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import csaboss.scanit.R;
import csaboss.scanit.ScanITApplication;
import csaboss.scanit.model.Document;


public class DocumentCapureActivity extends AppCompatActivity implements DocumentCapureScreen {
    TextView tvOcrResult;
    TextView tvTitle;
    SurfaceView cameraView;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;
    SparseArray<TextBlock> items;

    @Inject
    DocumentCapturePresenter documentCapturePresenter;

    public void capture() {
        //TODO btn click
        Document document = new Document();
        documentCapturePresenter.saveDocument(document);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_capture);
        tvOcrResult = (TextView) findViewById(R.id.ocr_result);
        tvTitle = (TextView) findViewById(R.id.document_title);
        cameraView = (SurfaceView) findViewById(R.id.surface_view);

        TextRecognizer tR = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!tR.isOperational()) {
            Log.w(DocumentCapureActivity.class.toString(), "Detector dependencies are not available");
        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), tR)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(DocumentCapureActivity.this, new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());
                    } catch (Exception e) {

                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    cameraSource.stop();
                }
            });

            tR.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {

                    items = detections.getDetectedItems();
                }
            });
        }


        Button btCapture = (Button) findViewById(R.id.capture);
        btCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items.size() != 0) {
                    tvTitle.setText(items.valueAt(0).getValue());
                    tvOcrResult.post(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder sB = new StringBuilder();
                            //int to = items.size() <= 3 ? items.size() : 3;
                            if (items.size() > 1) {
                                TextBlock item = items.valueAt(1);
                                List<? extends Text> components = item.getComponents();
                                int to = components.size() <= 2 ? items.size() : 2;
                                for (int i = 0; i < to; i++) {
                                    sB.append(components.get(i).getValue());
                                    sB.append("\n");
                                }
                                sB.append("...");
                                tvOcrResult.setText(sB.toString());
                            }

                        }
                    });
                }
            }
        });


        //TODO inject
        ScanITApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        documentCapturePresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        documentCapturePresenter.detachScreen();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void showSuccess(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


}
