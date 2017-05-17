package csaboss.scanit;


import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

public class TestHelper {

	public static void setTestInjector() {
		ShadowLog.stream = System.out;
		ScanITApplication application = (ScanITApplication) RuntimeEnvironment.application;
		ScanITApplicationComponent injector =DaggerTestComponent.builder().testModule(new TestModule(application.getApplicationContext())).build();
		application.setInjector(injector);
	}
}