package csaboss.scanit.ui.documentlist;

import java.util.List;

import csaboss.scanit.model.Document;

public interface DocumentListScreen {
    void showDocumentList(List<Document> documents);
    void showError(String error);
}