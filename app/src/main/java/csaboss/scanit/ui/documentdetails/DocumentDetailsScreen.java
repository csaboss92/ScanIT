package csaboss.scanit.ui.documentdetails;

import csaboss.scanit.model.Document;

public interface DocumentDetailsScreen {
    void showDocument(Document document);
    void deleteSuccess(String text);
    void showError(String text);
}