package csaboss.scanit.interactor.user.events;

import java.util.List;

import csaboss.scanit.model.User;

public class GetUserEvent {
    private int code;
	private User user;
	private Throwable throwable;

	//<editor-fold desc="Constructors|Getters|Setters">

	public GetUserEvent() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

//</editor-fold>
}