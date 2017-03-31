package csaboss.scanit.ui.documentdetails;

public interface DocumentDetailsScreen {
    void showDocument(String document);
    void deleteSuccess(String text);
    void deleteFailed(String text);
}