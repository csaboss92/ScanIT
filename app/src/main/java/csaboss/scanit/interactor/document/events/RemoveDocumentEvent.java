package csaboss.scanit.interactor.document.events;

public class RemoveDocumentEvent {
    private int code;
	private Throwable throwable;

	//<editor-fold desc="Constructors|Getters|Setters">

	public RemoveDocumentEvent() {
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