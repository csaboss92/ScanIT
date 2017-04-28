package csaboss.scanit.interactor.document.events;

import java.util.List;

import csaboss.scanit.model.Document;
import csaboss.scanit.model.User;

public class GetDocumentsEvent {
    private int code;
	private List<Document> documents;
	private Throwable throwable;

	//<editor-fold desc="Constructors|Getters|Setters">

	public GetDocumentsEvent() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

//</editor-fold>
}