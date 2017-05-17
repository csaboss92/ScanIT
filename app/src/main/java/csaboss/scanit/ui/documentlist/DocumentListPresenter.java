package csaboss.scanit.ui.documentlist;


import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import csaboss.scanit.interactor.document.DocumentInteractor;
import csaboss.scanit.interactor.document.events.AddDocumentEvent;
import csaboss.scanit.interactor.document.events.GetDocumentsEvent;
import csaboss.scanit.interactor.user.UserInteractor;
import csaboss.scanit.ui.Presenter;
import de.greenrobot.event.EventBus;

import static csaboss.scanit.ScanITApplication.injector;


public class DocumentListPresenter extends Presenter<DocumentListScreen> {
    @Inject
    DocumentInteractor documentInteractor;
    @Inject
    UserInteractor userInteractor;
    @Inject
    Executor executor;
    @Inject
    EventBus bus;


    public DocumentListPresenter() {
    }

    @Override
    public void attachScreen(DocumentListScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void getDocuments() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                documentInteractor.getDocuments();
            }
        });
    }
    public void logout() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userInteractor.deleteUser();
            }
        });

    }

    public void onEventMainThread(GetDocumentsEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError("Error saving document!");
            }
        } else {
            if (screen != null) {
                screen.showDocumentList(event.getDocuments());
            }
        }
    }
}