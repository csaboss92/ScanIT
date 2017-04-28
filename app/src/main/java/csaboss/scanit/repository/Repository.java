package csaboss.scanit.repository;

import android.content.Context;

import java.util.List;

import csaboss.scanit.model.Document;
import csaboss.scanit.model.User;

public interface Repository {

    void open(Context context);

    void close();

    User getUser();

    void saveUser(User user);

    void deleteUser(User user);

    Document getDocument(long id);

    List<Document> getDocuments();

    void addDocument(Document document);

    void removeDocument(Document document);
}