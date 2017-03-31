package csaboss.scanit;

import javax.inject.Singleton;

import csaboss.scanit.ui.UIModule;
import csaboss.scanit.ui.login.LoginActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class})
public interface ScanITApplicationComponent {
	void inject(LoginActivity mainActivity);

}