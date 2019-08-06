package tech.aftershock.athenaeum.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("subject")
    private Integer subject;

    @SerializedName("time")
    @Expose
    private Integer time;

    @SerializedName("views")
    @Expose
    private Integer views;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("file")
    @Expose
    private String file;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getSubject() {
        return subject;
    }

    public Integer getTime() {
        return time;
    }

    public Integer getViews() {
        return views;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getFile() {
        return file;
    }
}
