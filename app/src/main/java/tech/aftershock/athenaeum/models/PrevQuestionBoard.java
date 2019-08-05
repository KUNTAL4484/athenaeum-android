package tech.aftershock.athenaeum.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrevQuestionBoard {

    @SerializedName("board_name")
    @Expose
    private String boardName;

    @SerializedName("board_year")
    @Expose
    private Integer boardYear;

    @SerializedName("papers")
    @Expose
    private List<PreviousQuestionPaper> papers;

    public String getBoardName() {
        return boardName;
    }

    public Integer getBoardYear() {
        return boardYear;
    }

    public List<PreviousQuestionPaper> getPapers() {
        return papers;
    }
}
