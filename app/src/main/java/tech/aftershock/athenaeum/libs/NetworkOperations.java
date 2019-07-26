package tech.aftershock.athenaeum.libs;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tech.aftershock.athenaeum.models.SignInResponse;

public interface NetworkOperations {

    @FormUrlEncoded
    @POST("authentication/signin")
    Call<SignInResponse> signIn(@Field("username") String username, @Field("password") String password);
}
