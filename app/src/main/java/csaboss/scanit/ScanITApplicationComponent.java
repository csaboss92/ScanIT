package csaboss.scanit;

import javax.inject.Singleton;

import csaboss.scanit.ui.UIModule;
import csaboss.scanit.ui.documentcapture.DocumentCapureActivity;
import csaboss.scanit.ui.documentdetails.DocumentDetailsActivity;
import csaboss.scanit.ui.documentlist.DocumentListActivity;
import csaboss.scanit.ui.login.LoginActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class})
public interface ScanITApplicationComponent {
	void inject(LoginActivity loginActivity);
	void inject(DocumentListActivity documentListActivity);
	void inject(DocumentDetailsActivity documentDetailsPresenter);
	void inject(DocumentCapureActivity documentDetailsPresenter);
}