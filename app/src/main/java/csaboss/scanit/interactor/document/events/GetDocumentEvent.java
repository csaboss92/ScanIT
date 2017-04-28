package csaboss.scanit.interactor.document.events;

import csaboss.scanit.model.Document;
import csaboss.scanit.model.User;

public class GetDocumentEvent {
    private int code;
	private Document document;
	private Throwable throwable;

	//<editor-fold desc="Constructors|Getters|Setters">

	public GetDocumentEvent() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

//</editor-fold>
}