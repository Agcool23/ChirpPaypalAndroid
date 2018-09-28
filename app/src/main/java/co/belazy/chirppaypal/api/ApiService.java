package co.belazy.chirppaypal.api;

import co.belazy.chirppaypal.model.TokenResponseModel;
import co.belazy.chirppaypal.model.PayerID;
import co.belazy.chirppaypal.model.PaymentModel;
import co.belazy.chirppaypal.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by GadgetFreak on 13-09-2018.
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("v1/oauth2/token")
    Call<TokenResponseModel> getToken(@Header("Authorization") String Header,
                                      @Field("grant_type") String grantType);

    @POST("v1/payments/payment")
    Call<ResponseModel> payment(@Header("Authorization") String Header,
                                @Body PaymentModel obj);

    @POST("v1/payments/payment/{paymentId}/execute")
    Call<String> execute(@Header("Authorization") String Header,
                         @Path("paymentId") String paymentId,
                         @Body PayerID id);
}
