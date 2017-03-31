package csaboss.scanit;

import javax.inject.Singleton;

import csaboss.scanit.ui.UIModule;
import csaboss.scanit.ui.main.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class})
public interface ScanITApplicationComponent {
	void inject(MainActivity mainActivity);

}