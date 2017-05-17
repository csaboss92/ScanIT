package csaboss.scanit.ui.login;


import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import csaboss.scanit.interactor.user.UserInteractor;
import csaboss.scanit.interactor.user.events.GetUserEvent;
import csaboss.scanit.interactor.user.events.LoginUserEvent;
import csaboss.scanit.interactor.user.events.SaveUserEvent;
import csaboss.scanit.model.User;
import csaboss.scanit.network.api.UserApi;
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
    @Inject
    UserApi userApi;

    public LoginPresenter() {
    }

    public void login(final String name, final String password) {
        userInteractor.login(name, password);
    }


    public void getUser() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userInteractor.getUser();
            }
        });
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

    public void onEventMainThread(GetUserEvent event) {
        if (screen != null) {
            if (event.getUser() != null) {
                screen.loginSuccess();
            }
        }
    }

    public void onEventMainThread(final LoginUserEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.loginError("Network error");
            }
        } else {
            if (screen != null) {
                if (event.isSuccess()) {
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            userInteractor.saveUser(event.getUser());
                        }
                    });
                } else {
                    screen.loginError("Login Failed!");
                }
            }
        }


    }
}