package csaboss.scanit.ui.documentdetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import csaboss.scanit.R;
import csaboss.scanit.ScanITApplication;
import csaboss.scanit.model.Document;

public class DocumentDetailsActivity extends AppCompatActivity implements DocumentDetailsScreen {

    @Inject
    DocumentDetailsPresenter documentDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ScanITApplication.injector.inject(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        documentDetailsPresenter.attachScreen(this);
        documentDetailsPresenter.getDocument(5);
    }

    @Override
    protected void onStop() {
        super.onStop();
        documentDetailsPresenter.detachScreen();
    }

    @Override
    public void showDocument(Document document) {
        Toast.makeText(this, document.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteSuccess(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
