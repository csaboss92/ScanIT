package csaboss.scanit.interactor.user.events;

import csaboss.scanit.model.User;

public class DeleteUserEvent {
    private int code;
	private Throwable throwable;

	//<editor-fold desc="Constructors|Getters|Setters">

	public DeleteUserEvent() {
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