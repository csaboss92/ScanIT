package csaboss.scanit.ui.documentlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import csaboss.scanit.R;
import csaboss.scanit.ScanITApplication;
import csaboss.scanit.model.Document;
import csaboss.scanit.ui.documentcapture.DocumentCapureActivity;
import csaboss.scanit.ui.documentdetails.DocumentDetailsActivity;

public class DocumentListActivity extends AppCompatActivity implements DocumentListScreen {

    @Inject
    DocumentListPresenter documentListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_list);

        ScanITApplication.injector.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_documentlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addDocument:
                Intent i = new Intent(this, DocumentCapureActivity.class);
                startActivity(i);
                break;
            case R.id.logout:
                //TODO
                break;
        }

        return super.onOptionsItemSelected(item);
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
    public void showDocumentList(List<Document> documents) {
        Toast.makeText(this, documents.get(0).getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
