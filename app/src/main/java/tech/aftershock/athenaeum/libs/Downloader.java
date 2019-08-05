package tech.aftershock.athenaeum.libs;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class Downloader {

    private Activity mContext;
    private DownloadManager mDownloadManager;

    public Downloader(Activity context) {
        mContext = context;
        mDownloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public void startDownload(int contentType, String title, String file) {
        if(isStoragePermissionGranted()) {
            Uri downloadUri = Uri.parse(getServerSource(contentType) + file);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setAllowedOverRoaming(false);
            request.setTitle(title);
            request.setVisibleInDownloadsUi(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE | DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, getSaveAddress(contentType, title));

            mDownloadManager.enqueue(request);
        }
        else
            Toast.makeText(mContext, "Please grant storage permission", Toast.LENGTH_SHORT).show();
    }

    private String getServerSource(int contentType) {
        switch (contentType) {
            case StaticConstant.ContentType.PREV_QUESTION: return NetworkClient.getPreviousQuestionBase();
            default: return NetworkClient.getPreviousQuestionBase();
        }
    }

    private String getSaveAddress(int contentType, String title) {
        switch (contentType) {
            case StaticConstant.ContentType.PREV_QUESTION: return "/Athenaeum/" + "Previous Year Question Paper/" + title + ".pdf";
            default: return "/Athenaeum/" + "Previous Year Question Paper/" + title + ".pdf";
        }
    }

    private boolean isStoragePermissionGranted() {
        boolean status = false;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(mContext.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                status = true;
            else {
                status = false;
                ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        else
            status = true;

        return status;
    }
}
