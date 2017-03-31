package csaboss.scanit.ui;

import android.content.Context;


import javax.inject.Singleton;

import csaboss.scanit.ui.documentcapture.DocumentCapturePresenter;
import csaboss.scanit.ui.documentdetails.DocumentDetailsPresenter;
import csaboss.scanit.ui.documentlist.DocumentListPresenter;
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
    public LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }
    @Provides
    @Singleton
    public DocumentListPresenter provideDocumentListPresenter() {
        return new DocumentListPresenter();
    }

    @Provides
    @Singleton
    public DocumentDetailsPresenter provideDocumentDetailsPresenter() {
        return new DocumentDetailsPresenter();
    }
    @Provides
    @Singleton
    public DocumentCapturePresenter provideDocumentCapturePresenter() {
        return new DocumentCapturePresenter();
    }
}