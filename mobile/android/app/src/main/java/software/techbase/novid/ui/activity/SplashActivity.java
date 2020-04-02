package software.techbase.novid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import software.techbase.novid.R;
import software.techbase.novid.cache.sharepreferences.UserInfoStorage;
import software.techbase.novid.component.android.xlogger.XLogger;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(this::goToNextScreen, 1000);
    }

    private void goToNextScreen() {

        if (UserInfoStorage.getInstance().isUserLoggedIn()) {

            XLogger.debug(this.getClass(), "User Id : " + UserInfoStorage.getInstance().getUserId());
            XLogger.debug(this.getClass(), "Token : " + UserInfoStorage.getInstance().getAccessToken());

            this.startActivity(new Intent(this, MainActivity.class));
        } else {
            this.startActivity(new Intent(this, EntryActivity.class));
        }
        this.finish();
    }
}
