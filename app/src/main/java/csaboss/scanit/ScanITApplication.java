package csaboss.scanit;

import android.app.Application;

import csaboss.scanit.ui.UIModule;

public class ScanITApplication extends Application {

	public static ScanITApplicationComponent injector;

	@Override
	public void onCreate() {
		super.onCreate();

		injector =
				DaggerScanITApplicationComponent.builder().
						uIModule(
								new UIModule(this)
						).build();
	}
}