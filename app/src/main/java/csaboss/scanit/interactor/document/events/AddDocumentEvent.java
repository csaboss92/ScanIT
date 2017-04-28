package csaboss.scanit.interactor.document.events;

import csaboss.scanit.model.Document;

public class AddDocumentEvent {
    private int code;
	private Throwable throwable;

	//<editor-fold desc="Constructors|Getters|Setters">

	public AddDocumentEvent() {
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