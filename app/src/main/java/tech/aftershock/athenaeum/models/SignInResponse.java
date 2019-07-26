package tech.aftershock.athenaeum.models;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("error_msg")
    private String errorMsg;

    @SerializedName("user")
    private User user;

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public User getUser() {
        return user;
    }
}
