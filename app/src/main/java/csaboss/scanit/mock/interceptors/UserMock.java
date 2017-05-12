package csaboss.scanit.mock.interceptors;

import android.net.Uri;

import csaboss.scanit.model.User;
import csaboss.scanit.network.NetworkConfig;

import csaboss.scanit.utils.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static csaboss.scanit.mock.interceptors.MockHelper.makeResponse;

/**
 * Created by Csabi on 2017. 05. 12..
 */

class UserMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());
        User user = new User(1L,"Jani","asdasd");
        String responseString;
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "user/login") && request.method().equals("Get")) {
            User postedUser = GsonHelper.getGson().fromJson(request.body().toString(), User.class);

            if (user.getName().equals(postedUser.getName())&&user.getPassword().equals(postedUser.getPassword()))
            {
                responseString = "Success";
                responseCode = 200;
            } else {
                responseString = "Failed";
                responseCode = 403;
            }

        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
