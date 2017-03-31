package csaboss.scanit.ui.documentlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import csaboss.scanit.R;
import csaboss.scanit.ScanITApplication;

public class DocumentListActivity extends AppCompatActivity implements DocumentListScreen {

    @Inject
    DocumentListPresenter documentListPresenter;

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
        documentListPresenter.attachScreen(this);
        documentListPresenter.getDocuments();
    }

    @Override
    protected void onStop() {
        super.onStop();
        documentListPresenter.detachScreen();
    }

    @Override
    public void showDocumentList(List<String> documents) {
        Toast.makeText(this, documents.get(0), Toast.LENGTH_SHORT).show();
    }
}
