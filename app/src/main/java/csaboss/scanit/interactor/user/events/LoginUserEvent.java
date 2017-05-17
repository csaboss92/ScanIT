package csaboss.scanit.interactor.user.events;

import csaboss.scanit.model.User;

public class LoginUserEvent {
    private int code;
	private Throwable throwable;
	private User user;
	private boolean success;


//<editor-fold desc="Constructors|Getters|Setters">

	public LoginUserEvent() {
	}
	public User getUser() {
		return user;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

//</editor-fold>
}