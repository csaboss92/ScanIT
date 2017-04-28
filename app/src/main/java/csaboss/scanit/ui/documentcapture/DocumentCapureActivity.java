package csaboss.scanit.ui.documentcapture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import csaboss.scanit.R;
import csaboss.scanit.ScanITApplication;
import csaboss.scanit.model.Document;

public class DocumentCapureActivity extends AppCompatActivity implements DocumentCapureScreen {

    @Inject
    DocumentCapturePresenter documentCapturePresenter;

    public void capture(){
        //TODO btn click
        Document document = new Document();
        documentCapturePresenter.saveDocument(document);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void showSuccess(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
