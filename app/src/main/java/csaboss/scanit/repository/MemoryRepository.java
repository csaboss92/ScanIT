package csaboss.scanit.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import csaboss.scanit.model.Document;
import csaboss.scanit.model.User;

public class MemoryRepository implements Repository {


    public static List<Document> documents;
    public static User user;


    @Override
    public void open(Context context) {
        Document d1 = new Document(1L, "Cim1", "Ez itt a tartalma a cuccnak");
        Document d2 = new Document(2L, "Cim2", "Ez itt a tartalma a cuccnak 2");
        documents = new ArrayList<>();
        documents.add(d1);
        documents.add(d2);
        user = new User(1L, "Jenő", "Test01");
    }

    @Override
    public void close() {

    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void saveUser(User user) {
        this.user = user;
    }

    @Override
    public void deleteUser(User user) {
        this.user = null;
    }

    @Override
    public Document getDocument(long id) {
        for (Document doc : documents) {
            if (doc.getId() == id) {
                return doc;
            }
        }
        return null;
    }

    @Override
    public List<Document> getDocuments(long id) {
        return documents;
    }

    @Override
    public void addDocument(Document document) {
        documents.add(document);
    }

    @Override
    public void removeDocument(Document document) {
        documents.remove(document);
    }

    @Override
    public boolean isInDB(Document document) {
        return documents.contains(document);
    }
}