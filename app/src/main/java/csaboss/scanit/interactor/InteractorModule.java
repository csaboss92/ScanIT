package csaboss.scanit.interactor;

import csaboss.scanit.interactor.document.DocumentInteractor;
import csaboss.scanit.interactor.user.UserInteractor;
import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {


	@Provides
    public UserInteractor provideUser() {
		return new UserInteractor();
	}

	@Provides
	public DocumentInteractor provideDocument() {
		return new DocumentInteractor();
	}


}