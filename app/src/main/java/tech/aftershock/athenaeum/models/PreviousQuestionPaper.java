package tech.aftershock.athenaeum.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreviousQuestionPaper {

    @SerializedName("paper_id")
    @Expose
    private String paperId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("year")
    @Expose
    private String year;

    @SerializedName("semester")
    @Expose
    private String semester;

    @SerializedName("stream")
    @Expose
    private String stream;

    @SerializedName("file")
    @Expose
    private String file;

    public String getPaperId() {
        return paperId;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }

    public String getStream() {
        return stream;
    }

    public String getFile() {
        return file;
    }
}
