package csaboss.scanit.ui.login;


import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import csaboss.scanit.interactor.user.UserInteractor;
import csaboss.scanit.interactor.user.events.SaveUserEvent;
import csaboss.scanit.model.User;
import csaboss.scanit.ui.Presenter;
import de.greenrobot.event.EventBus;

import static csaboss.scanit.ScanITApplication.injector;

public class LoginPresenter extends Presenter<LoginScreen> {
    @Inject
    UserInteractor userInteractor;
    @Inject
    Executor executor;
    @Inject
    EventBus bus;

    public LoginPresenter() {
    }

    public void login(final String name, final String password) {

        //TODO hálózat
        if (1 == 1) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    User user = new User(null, name, password);
                    userInteractor.saveUser(user);
                }
            });
        } else {
            screen.loginError("Login Failed!");
        }
    }

    @Override
    public void attachScreen(LoginScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void onEventMainThread(SaveUserEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.loginError("error");
            }
        } else {
            if (screen != null) {
                screen.loginSuccess();
            }
        }
    }
}