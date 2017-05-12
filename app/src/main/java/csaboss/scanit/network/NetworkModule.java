package csaboss.scanit.network;

import javax.inject.Singleton;

import csaboss.scanit.network.api.DocumentApi;
import csaboss.scanit.network.api.UserApi;
import csaboss.scanit.utils.GsonHelper;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVICE_ENDPOINT).client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson())).build();
    }

    @Provides
    @Singleton
    public DocumentApi provideADocumentApi(Retrofit retrofit) {
        return retrofit.create(DocumentApi.class);
    }

    @Provides
    @Singleton
    public UserApi provideAUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

}
