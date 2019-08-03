package tech.aftershock.athenaeum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.aftershock.athenaeum.libs.AuthProgressDialog;
import tech.aftershock.athenaeum.libs.NetworkClient;
import tech.aftershock.athenaeum.libs.NetworkOperations;
import tech.aftershock.athenaeum.libs.StaticConstant;
import tech.aftershock.athenaeum.libs.Stdlib;
import tech.aftershock.athenaeum.models.SignInResponse;
import tech.aftershock.athenaeum.models.User;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private View mRoot;
    private EditText mUsername, mPassword;
    private Button mSignInBtn;
    private TextView mForgotPassword;

    private NetworkOperations mOperation;
    private AuthProgressDialog mDialog;

    private Call<SignInResponse> mSignInCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mRoot = findViewById(R.id.signin_root);
        mUsername = findViewById(R.id.signin_username);
        mPassword = findViewById(R.id.signin_password);
        mForgotPassword = findViewById(R.id.signin_forgot_password);
        mSignInBtn = findViewById(R.id.signin_btn);

        mOperation = NetworkClient.getOperations(getApplicationContext());

        mSignInBtn.setOnClickListener(this);

        mDialog = new AuthProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin_btn: performSignIn(); break;
        }
    }

    private void performSignIn() {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()) {
            Stdlib.showErrorSnack(mRoot, "Please enter email & password");
        }
        else {
            mDialog.showProgress(this);
            mSignInCall = mOperation.signIn(username, password);
            mSignInCall.enqueue(new Callback<SignInResponse>() {
                @Override
                public void onResponse(@NonNull Call<SignInResponse> call, @NonNull Response<SignInResponse> response) {
                    mDialog.dismissDialog();
                    if(response.isSuccessful()) {
                        SignInResponse signInResponse = response.body();
                        if(signInResponse != null) {
                            if(signInResponse.isSuccess()) {
                                signInSuccessful(signInResponse.getUser());
                                Stdlib.showErrorSnack(mRoot, "Working");
                            }
                            else {
                                Stdlib.showErrorSnack(mRoot, signInResponse.getErrorMsg());
                            }
                        }
                        else
                            Stdlib.showErrorSnack(mRoot, "Something wrong happened, try again");
                    }
                    else
                        Stdlib.showErrorSnack(mRoot, "Something wrong happned, try again");
                }

                @Override
                public void onFailure(@NonNull Call<SignInResponse> call, Throwable t) {
                    t.printStackTrace();
                    mDialog.dismissDialog();
                    if(!call.isCanceled()) {
                        Stdlib.showErrorSnack(mRoot, "Something wrong happned, try again");
                    }
                }
            });
        }
    }

    private void signInSuccessful(User user) {
        SharedPreferences preferences = getSharedPreferences(StaticConstant.PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(StaticConstant.SIGN_IN, true);
        editor.putString(StaticConstant.User.ID, user.getUserId());
        editor.putString(StaticConstant.User.NAME, user.getName());
        editor.putString(StaticConstant.User.EMAIL, user.getEmail());
        editor.putString(StaticConstant.User.DEPARTMENT_ID, user.getDepartmentId());
        editor.putString(StaticConstant.User.DEPARTMENT_NAME, user.getDepartmentName());
        editor.putString(StaticConstant.User.CLASS_ROLL, user.getClassRoll());
        editor.putString(StaticConstant.User.SEMESTER, user.getSemester());

        editor.apply();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}