package tech.aftershock.athenaeum.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoBoard {

    @SerializedName("subject_id")
    @Expose
    private Integer boardId;

    @SerializedName("subject_name")
    @Expose
    private String boardName;

    @SerializedName("videos")
    @Expose
    private List<Video> videos;

    public Integer getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
