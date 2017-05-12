package csaboss.scanit.network.api;

import java.util.List;

import csaboss.scanit.network.model.Document;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DocumentApi {
  
  /**
   * Returns all documents of the user
   * 
   * @return Call<List<Document>>
   */
  
  @GET("document")
  Call<List<Document>> documentGet();
    

  
  /**
   * Creates a new document for the user
   * 
   * @param body 
   * @return Call<Void>
   */
  
  @POST("document")
  Call<Void> documentPost(
          @Body Document body
  );

  
  /**
   * Returns the specified document
   * 
   * @param id ID of document
   * @return Call<Document>
   */
  
  @GET("document/{id}")
  Call<Document> documentIdGet(
          @Path("id") Long id
  );

  
  /**
   * Deletes the specified document
   * 
   * @param id ID of document
   * @return Call<Void>
   */
  
  @DELETE("document/{id}")
  Call<Void> documentIdDelete(
          @Path("id") Long id
  );

  
}
