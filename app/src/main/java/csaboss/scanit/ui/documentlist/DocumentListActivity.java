package csaboss.scanit.ui.documentlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import csaboss.scanit.R;
import csaboss.scanit.ScanITApplication;
import csaboss.scanit.model.Document;
import csaboss.scanit.ui.documentcapture.DocumentCapureActivity;
import csaboss.scanit.ui.login.LoginPresenter;

public class DocumentListActivity extends AppCompatActivity implements DocumentListScreen {

    private RecyclerView recyclerView;
    private DocumentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Document> documents;

    @Inject
    DocumentListPresenter documentListPresenter;
    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_document_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DocumentAdapter(documents);
        recyclerView.setAdapter(adapter);
        setTitle("Documents");
        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab_add);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startCaptureActivity();
            }
        });

        ScanITApplication.injector.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_document_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addDocument:
                startCaptureActivity();
                break;
            case R.id.logout:
                documentListPresenter.logout();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                    break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startCaptureActivity() {
        Intent i = new Intent(this, DocumentCapureActivity.class);
        startActivity(i);
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
        this.documents = documents;
        adapter.updateDocumentList(this.documents);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
