package csaboss.scanit.mock.interceptors;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import csaboss.scanit.model.Document;
import csaboss.scanit.network.NetworkConfig;
import csaboss.scanit.repository.MemoryRepository;
import csaboss.scanit.utils.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static csaboss.scanit.mock.interceptors.MockHelper.makeResponse;


public class DocumentMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());
        List<Document> documents;
        Document d1 = new Document(1L, "Cim1", "Ez itt a tartalma a cuccnak");
        Document d2 = new Document(2L, "Cim2", "Ez itt a tartalma a cuccnak 2");
        Document d3 = new Document(3L, "Cim3", "Ez itt a tartalma a cuccnak 3");
        Document d4 = new Document(4L, "Cim4", "Ez itt a tartalma a cuccnak 4");
        documents = new ArrayList<>();
        documents.add(d1);
        documents.add(d2);
        documents.add(d3);
        documents.add(d4);
        String responseString;
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "document") && request.method().equals("POST")) {
            responseString = "Success";
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "document") && request.method().equals("Get")) {
            responseString = GsonHelper.getGson().toJson(documents);
            responseCode = 200;
        } else if (uri.getPath().matches("\\" + NetworkConfig.ENDPOINT_PREFIX + "document\\/" + "\\d+") && request.method().equals("Get")) {
            long id = Long.parseLong(uri.getPath().substring(uri.getPath().lastIndexOf("/")));
            Document ret = null;
            for (Document doc : documents) {
                if (doc.getId() == id) {
                    ret = doc;
                }
            }
            if (ret == null) {
                responseString = "Not found";
                responseCode = 404;
            } else {
                responseString = GsonHelper.getGson().toJson(ret);
                responseCode = 200;
            }
        } else if (uri.getPath().matches("\\" + NetworkConfig.ENDPOINT_PREFIX + "document\\/" + "\\d+") && request.method().equals("Delete")) {
            responseString = "Success";
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}