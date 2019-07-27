package tech.aftershock.athenaeum.models;

public class Stream {

    private int mId;
    private String mTitle;
    private int mBannerId;

    public Stream(int mId, String mTitle, int mBannerId) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mBannerId = mBannerId;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getBannerId() {
        return mBannerId;
    }
}
