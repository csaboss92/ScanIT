package csaboss.scanit;

import android.app.Application;

import javax.inject.Inject;

import csaboss.scanit.repository.Repository;
import csaboss.scanit.ui.UIModule;

public class ScanITApplication extends Application {

    @Inject
    Repository repository;

    public static ScanITApplicationComponent injector;

    public void setInjector(ScanITApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerScanITApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();

        injector.inject(this);
        repository.open(getApplicationContext());
    }
}