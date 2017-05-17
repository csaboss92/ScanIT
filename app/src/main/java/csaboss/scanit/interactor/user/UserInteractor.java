package csaboss.scanit.interactor.user;

import com.orm.SugarRecord;

import java.io.IOException;

import javax.inject.Inject;

import csaboss.scanit.ScanITApplication;
import csaboss.scanit.interactor.user.events.DeleteUserEvent;
import csaboss.scanit.interactor.user.events.GetUserEvent;
import csaboss.scanit.interactor.user.events.LoginUserEvent;
import csaboss.scanit.interactor.user.events.SaveUserEvent;
import csaboss.scanit.model.User;
import csaboss.scanit.network.api.UserApi;
import csaboss.scanit.repository.Repository;
import de.greenrobot.event.EventBus;
import retrofit2.Response;

public class UserInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;
    @Inject
    UserApi userApi;

    public UserInteractor() {
        ScanITApplication.injector.inject(this);
    }

    public void login(final String username, final String password) {
        LoginUserEvent event = new LoginUserEvent();
        try {
            Response response = userApi.userLoginGet().execute();
            event.setSuccess(response.isSuccess());
            if (response.isSuccess()) {
                User user = new User();
                user.setName(username);
                user.setPassword(password);
                event.setUser(user);
            }
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void getUser() {
        GetUserEvent event = new GetUserEvent();
        try {
            User user = repository.getUser();
            event.setUser(user);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveUser(User user) {
        SaveUserEvent event = new SaveUserEvent();
        try {
            repository.saveUser(user);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void deleteUser() {
        DeleteUserEvent event = new DeleteUserEvent();
        try {
            User user = repository.getUser();
            repository.deleteUser(user);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}