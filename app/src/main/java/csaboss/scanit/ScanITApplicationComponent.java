package csaboss.scanit;

import javax.inject.Singleton;

import csaboss.scanit.interactor.InteractorModule;
import csaboss.scanit.interactor.document.DocumentInteractor;
import csaboss.scanit.interactor.user.UserInteractor;
import csaboss.scanit.network.NetworkModule;
import csaboss.scanit.repository.Repository;
import csaboss.scanit.repository.RepositoryModule;
import csaboss.scanit.ui.UIModule;
import csaboss.scanit.ui.documentcapture.DocumentCapturePresenter;
import csaboss.scanit.ui.documentcapture.DocumentCapureActivity;
import csaboss.scanit.ui.documentdetails.DocumentDetailsActivity;
import csaboss.scanit.ui.documentdetails.DocumentDetailsPresenter;
import csaboss.scanit.ui.documentlist.DocumentListActivity;
import csaboss.scanit.ui.documentlist.DocumentListPresenter;
import csaboss.scanit.ui.login.LoginActivity;
import csaboss.scanit.ui.login.LoginPresenter;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class, NetworkModule.class})
public interface ScanITApplicationComponent {
    void inject(LoginActivity loginActivity);

    void inject(DocumentListActivity documentListActivity);

    void inject(DocumentDetailsActivity documentDetailsPresenter);

    void inject(DocumentCapureActivity documentDetailsPresenter);

    void inject(UserInteractor userInteractor);

    void inject(DocumentInteractor documentInteractor);

    void inject(ScanITApplication scanITApplication);

    void inject(LoginPresenter loginPresenter);

    void inject(DocumentListPresenter documentListPresenter);

    void inject(DocumentDetailsPresenter documentDetailsPresenter);

    void inject(DocumentCapturePresenter documentCapturePresenter);
}