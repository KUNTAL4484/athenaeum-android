package tech.aftershock.athenaeum.libs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tech.aftershock.athenaeum.models.GetPrevQuestionBoardResponse;
import tech.aftershock.athenaeum.models.PreviousQuestionPaper;
import tech.aftershock.athenaeum.models.SignInResponse;
import tech.aftershock.athenaeum.models.Video;
import tech.aftershock.athenaeum.models.VideoBoard;

public interface NetworkOperations {

    @FormUrlEncoded
    @POST("authentication/signin")
    Call<SignInResponse> signIn(@Field("username") String username, @Field("password") String password);

    @GET("prevquestion/getYearQuestionPaper/{streamId}")
    Call<GetPrevQuestionBoardResponse> getPreviousYearQuestionBoard(@Path("streamId") int streamId);

    @GET("prevquestion/getquestionpaperlist/{stream}/{year}")
    Call<List<PreviousQuestionPaper>> getPreviousYearQuestionPaperList(@Path("stream") int stream, @Path("year") int year);

    @GET("video/getvideoboard")
    Call<List<VideoBoard>> getVideoBoard();

    @GET("video/getsubjectvideos/{subject}")
    Call<List<Video>> getSubjectVideos(@Path("subject") String subject);
}
