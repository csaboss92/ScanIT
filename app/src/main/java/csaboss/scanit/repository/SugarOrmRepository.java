package csaboss.scanit.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import csaboss.scanit.model.Document;
import csaboss.scanit.model.User;

public class SugarOrmRepository implements Repository {


    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public User getUser() {
        return SugarRecord.listAll(User.class).get(0);
    }

    @Override
    public void saveUser(User user) {
        SugarRecord.save(user);
    }

    @Override
    public void deleteUser(User user) {
        SugarRecord.delete(user);
    }

    @Override
    public Document getDocument(long id) {
        return SugarRecord.findById(Document.class, id);
    }

    @Override
    public List<Document> getDocuments() {
        return SugarRecord.listAll(Document.class);
    }

    @Override
    public void addDocument(Document document) {
        SugarRecord.save(document);
    }

    @Override
    public void removeDocument(Document document) {
        SugarRecord.delete(document);
    }


}