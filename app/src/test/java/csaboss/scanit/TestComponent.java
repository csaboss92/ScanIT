package csaboss.scanit;

import javax.inject.Singleton;


import csaboss.scanit.interactor.InteractorModule;
import csaboss.scanit.mock.MockNetworkModule;
import csaboss.scanit.repository.TestRepositoryModule;
import dagger.Component;

@Singleton
@Component(modules = {TestModule.class, TestRepositoryModule.class, InteractorModule.class, MockNetworkModule.class})
public interface TestComponent extends ScanITApplicationComponent {
}