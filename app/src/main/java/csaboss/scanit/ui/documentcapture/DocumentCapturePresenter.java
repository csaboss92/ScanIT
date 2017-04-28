package csaboss.scanit.ui.documentcapture;


import java.util.concurrent.Executor;

import javax.inject.Inject;

import csaboss.scanit.interactor.document.DocumentInteractor;
import csaboss.scanit.interactor.document.events.AddDocumentEvent;
import csaboss.scanit.model.Document;
import csaboss.scanit.ui.Presenter;
import de.greenrobot.event.EventBus;

import static csaboss.scanit.ScanITApplication.injector;


public class DocumentCapturePresenter extends Presenter<DocumentCapureScreen> {

    @Inject
    DocumentInteractor documentInteractor;
    @Inject
    Executor executor;
    @Inject
    EventBus bus;

    public DocumentCapturePresenter() {
    }

    @Override
    public void attachScreen(DocumentCapureScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void saveDocument(final Document document) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                documentInteractor.addDocument(document);
            }
        });
    }

    public void onEventMainThread(AddDocumentEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError("Error saving document!");
            }
        } else {
            if (screen != null) {
                screen.showSuccess("Successful save.");
            }
        }
    }
}