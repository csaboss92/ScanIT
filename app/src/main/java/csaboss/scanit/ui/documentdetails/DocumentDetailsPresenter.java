package csaboss.scanit.ui.documentdetails;


import java.util.concurrent.Executor;

import javax.inject.Inject;

import csaboss.scanit.interactor.document.DocumentInteractor;
import csaboss.scanit.interactor.document.events.AddDocumentEvent;
import csaboss.scanit.interactor.document.events.GetDocumentEvent;
import csaboss.scanit.interactor.document.events.RemoveDocumentEvent;
import csaboss.scanit.interactor.user.UserInteractor;
import csaboss.scanit.model.Document;
import csaboss.scanit.ui.Presenter;
import de.greenrobot.event.EventBus;

import static csaboss.scanit.ScanITApplication.injector;


public class DocumentDetailsPresenter extends Presenter<DocumentDetailsScreen> {

    @Inject
    UserInteractor userInteractor;
    @Inject
    DocumentInteractor documentInteractor;
    @Inject
    Executor executor;
    @Inject
    EventBus bus;

    public DocumentDetailsPresenter() {
    }

    public void logout() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userInteractor.deleteUser();
            }
        });

    }
    @Override
    public void attachScreen(DocumentDetailsScreen screen) {
        injector.inject(this);
        bus.register(this);
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void getDocument(final long id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                documentInteractor.getDocument(id);
            }
        });
    }

    public void deleteDocument(final Document document) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                documentInteractor.removeDocument(document);
            }
        });
    }

    public void onEventMainThread(GetDocumentEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError("Error loading document!");
            }
        } else {
            if (screen != null) {
                screen.showDocument(event.getDocument());
            }
        }
    }

    public void onEventMainThread(RemoveDocumentEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError("Error deleting document!");
            }
        } else {
            if (screen != null) {
                screen.deleteSuccess("Successful delete operation");
            }
        }
    }
}