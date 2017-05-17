package csaboss.scanit.interactor.document;

import com.orm.SugarRecord;

import java.util.List;

import javax.inject.Inject;

import csaboss.scanit.ScanITApplication;
import csaboss.scanit.interactor.document.events.AddDocumentEvent;
import csaboss.scanit.interactor.document.events.GetDocumentEvent;
import csaboss.scanit.interactor.document.events.GetDocumentsEvent;
import csaboss.scanit.interactor.document.events.RemoveDocumentEvent;
import csaboss.scanit.model.Document;
import csaboss.scanit.network.api.DocumentApi;
import csaboss.scanit.repository.Repository;
import de.greenrobot.event.EventBus;
import retrofit2.Response;

public class DocumentInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;
    @Inject
    DocumentApi documentApi;

    public DocumentInteractor() {
        ScanITApplication.injector.inject(this);
    }

    public void getDocument(long id) {
        GetDocumentEvent event = new GetDocumentEvent();
        try {
            Document document = repository.getDocument(id);
            if (document==null){
                documentApi.documentIdGet(id).execute();
            }
            event.setDocument(document);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void getDocuments() {
        GetDocumentsEvent event = new GetDocumentsEvent();
        try {
            //Logic needed to sync server and local db
            Response res= documentApi.documentGet().execute();
            List<Document> documents = repository.getDocuments();
            event.setDocuments(documents);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void addDocument(Document document) {
        AddDocumentEvent event = new AddDocumentEvent();
        try {
            csaboss.scanit.network.model.Document doc = new csaboss.scanit.network.model.Document();
            doc.setText(document.getText());
            doc.setTitle(document.getTitle());
            Response res = documentApi.documentPost(doc).execute();
            if (res.isSuccess()){
                repository.addDocument(document);
            }
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void removeDocument(Document document) {
        RemoveDocumentEvent event = new RemoveDocumentEvent();
        try {
            Response response = documentApi.documentIdDelete(document.getId()).execute();
            if(response.isSuccess()) {
                repository.removeDocument(document);
            }
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}