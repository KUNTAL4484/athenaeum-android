package tech.aftershock.athenaeum.libs;

import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import tech.aftershock.athenaeum.R;

public class AthProgressDialog {

    private AlertDialog mDialog = null;

    public void showProgress(Context context, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
        TextView progressMsg = view.findViewById(R.id.progress_msg);
        progressMsg.setText(msg);
        builder.setView(view);

        mDialog = builder.create();
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);

        mDialog.show();
    }

    public void showProgress(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
        TextView progressMsg = view.findViewById(R.id.progress_msg);
        progressMsg.setText("Please wait while we are loading...");
        builder.setView(view);

        mDialog = builder.create();
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);

        mDialog.show();
    }

    public void dismissDialog() {
        if(mDialog != null) {
            mDialog.dismiss();
        }
    }
}
