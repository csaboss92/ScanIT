package csaboss.scanit.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.robolectric.annotation.Config;

import java.util.List;

import csaboss.scanit.BuildConfig;
import csaboss.scanit.model.Document;
import csaboss.scanit.ui.documentlist.DocumentListPresenter;
import csaboss.scanit.ui.documentlist.DocumentListScreen;
import csaboss.scanit.ui.login.LoginPresenter;
import csaboss.scanit.ui.login.LoginScreen;
import csaboss.scanit.utils.RobolectricDaggerTestRunner;

import static csaboss.scanit.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginTest {

    private LoginPresenter loginPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        loginPresenter = new LoginPresenter();
    }

    @Test
    public void testLogin() {
        LoginScreen loginScreen = mock(LoginScreen.class);
        loginPresenter.attachScreen(loginScreen);
        loginPresenter.login("asd","asd");

        verify(loginScreen,times(1)).loginSuccess();
    }

    @After
    public void tearDown() {
        loginPresenter.detachScreen();
    }
}