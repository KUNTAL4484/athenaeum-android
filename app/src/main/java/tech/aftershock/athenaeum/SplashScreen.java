package tech.aftershock.athenaeum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import tech.aftershock.athenaeum.libs.StaticConstant;

public class SplashScreen extends AppCompatActivity {

    private TextView mAppName;

    private boolean mSigninStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAppName = findViewById(R.id.splash_screen_appname);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_appname);
        mAppName.setAnimation(animation);

        SharedPreferences preferences = getSharedPreferences(StaticConstant.PREFERENCE_NAME, MODE_PRIVATE);
        mSigninStatus = preferences.getBoolean(StaticConstant.SIGN_IN, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mSigninStatus)
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                else
                    startActivity(new Intent(SplashScreen.this, SignIn.class));
            }
        }, 2000);
    }
}
