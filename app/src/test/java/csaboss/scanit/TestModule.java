package csaboss.scanit;


import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import csaboss.scanit.ui.UIModule;
import csaboss.scanit.ui.documentcapture.DocumentCapturePresenter;
import csaboss.scanit.ui.documentdetails.DocumentDetailsPresenter;
import csaboss.scanit.ui.documentlist.DocumentListPresenter;
import csaboss.scanit.ui.login.LoginPresenter;
import csaboss.scanit.utils.UiExecutor;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

@Module
public class TestModule {

	private final UIModule UIModule;

	public TestModule(Context context) {
        this.UIModule = new UIModule(context);
	}

	@Provides
	public Context provideContext() {
		return UIModule.provideContext();
	}


	@Provides
	public LoginPresenter provideLoginPresenter() {
		return UIModule.provideLoginPresenter();
	}

	@Provides
	public DocumentListPresenter provideDocumentListPresenter(){
		return  UIModule.provideDocumentListPresenter();
	}
	@Provides
	public DocumentCapturePresenter provideDocumentCapturePresenter(){
		return  UIModule.provideDocumentCapturePresenter();
	}
	@Provides
	public DocumentDetailsPresenter provideDocumentDetailsPresenter(){
		return  UIModule.provideDocumentDetailsPresenter();
	}

	@Provides
	@Singleton
	public EventBus provideEventBus() {
		return EventBus.getDefault();
	}

	@Provides
	@Singleton
	public Executor provideNetworkExecutor() {
		return new UiExecutor();
	}


}