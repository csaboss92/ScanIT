package csaboss.scanit.interactor.user;

import com.orm.SugarRecord;

import javax.inject.Inject;

import csaboss.scanit.ScanITApplication;
import csaboss.scanit.interactor.user.events.DeleteUserEvent;
import csaboss.scanit.interactor.user.events.GetUserEvent;
import csaboss.scanit.interactor.user.events.SaveUserEvent;
import csaboss.scanit.model.User;
import csaboss.scanit.network.api.UserApi;
import csaboss.scanit.repository.Repository;
import de.greenrobot.event.EventBus;

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
            userApi.userLoginGet().execute();
            repository.saveUser(user);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void deleteUser(User user) {
        DeleteUserEvent event = new DeleteUserEvent();
        try {
            repository.deleteUser(user);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}