package tech.aftershock.athenaeum.libs;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tech.aftershock.athenaeum.models.GetPrevQuestionBoardResponse;
import tech.aftershock.athenaeum.models.SignInResponse;

public interface NetworkOperations {

    @FormUrlEncoded
    @POST("authentication/signin")
    Call<SignInResponse> signIn(@Field("username") String username, @Field("password") String password);

    @GET("prevquestion/getYearQuestionPaper/{streamId}")
    Call<GetPrevQuestionBoardResponse> getPreviousYearQuestionBoard(@Path("streamId") int streamId);
}
