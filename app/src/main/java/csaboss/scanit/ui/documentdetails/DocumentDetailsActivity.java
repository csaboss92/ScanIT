package csaboss.scanit.ui.documentdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import csaboss.scanit.R;
import csaboss.scanit.ScanITApplication;
import csaboss.scanit.model.Document;

public class DocumentDetailsActivity extends AppCompatActivity implements DocumentDetailsScreen {

    Document document;
    TextView title;
    TextView body;

    @Inject
    DocumentDetailsPresenter documentDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_details);

        title = (TextView) findViewById(R.id.document_title);
        body = (TextView) findViewById(R.id.document_body);

        ScanITApplication.injector.inject(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent i = getIntent();


        long documentId = i.getLongExtra("DocumentId", 0);

        documentDetailsPresenter.attachScreen(this);
        documentDetailsPresenter.getDocument(documentId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        documentDetailsPresenter.detachScreen();
    }

    @Override
    public void showDocument(Document document) {
        this.document=document;
        title.setText(document.getTitle());
        body.setText(document.getText());
    }

    @Override
    public void deleteSuccess(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_document_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove_document:
                documentDetailsPresenter.deleteDocument(document);
                finish();
                break;
            case R.id.logout:
                //TODO
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
