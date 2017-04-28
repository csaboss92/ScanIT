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
import csaboss.scanit.repository.Repository;
import de.greenrobot.event.EventBus;

public class DocumentInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public DocumentInteractor() {
        ScanITApplication.injector.inject(this);
    }

    public void getDocument(long id) {
        GetDocumentEvent event = new GetDocumentEvent();
        try {
            Document document = repository.getDocument(id);
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
            repository.addDocument(document);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void removeDocument(Document document) {
        RemoveDocumentEvent event = new RemoveDocumentEvent();
        try {
            repository.removeDocument(document);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}