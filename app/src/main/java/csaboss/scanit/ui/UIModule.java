package csaboss.scanit.ui;

import android.content.Context;


import javax.inject.Singleton;

import csaboss.scanit.ui.login.LoginPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public LoginPresenter provideMainPresenter() {
        return new LoginPresenter();
    }

}