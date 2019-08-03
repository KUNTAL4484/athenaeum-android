package tech.aftershock.athenaeum.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPrevQuestionBoardResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("error_msg")
    @Expose
    private String errorMsg;

    @SerializedName("boards")
    @Expose
    private List<PrevQuestionBoard> boards;

    public Boolean getSuccess() {
        return success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public List<PrevQuestionBoard> getBoards() {
        return boards;
    }
}
