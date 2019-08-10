package tech.aftershock.athenaeum.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("subject")
    private Subject subject;

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

    public Subject getSubject() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeParcelable(this.subject, flags);
        dest.writeValue(this.time);
        dest.writeValue(this.views);
        dest.writeString(this.description);
        dest.writeString(this.thumbnail);
        dest.writeString(this.file);
    }

    public Video() {
    }

    protected Video(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.subject = in.readParcelable(Subject.class.getClassLoader());
        this.time = (Integer) in.readValue(Integer.class.getClassLoader());
        this.views = (Integer) in.readValue(Integer.class.getClassLoader());
        this.description = in.readString();
        this.thumbnail = in.readString();
        this.file = in.readString();
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
