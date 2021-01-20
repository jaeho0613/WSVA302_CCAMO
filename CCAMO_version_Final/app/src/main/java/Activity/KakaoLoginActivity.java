package Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.kakao.sdk.user.UserApiClient;

import FireDatabase.FirebaseHelper;
import KaKaoAPI.LogIn.KakaoLogInManager;
import RoomDatabase.CafeDatabaseInit;
import Utility.MyUtility;
import Utility.SharedKey;
import Utility.UtilSharedPreference;
import team.wsva302.ccamo.R;

public class KakaoLoginActivity extends AppCompatActivity {

    KakaoLogInManager logInManager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 로그인 버튼
        Button buttonLogIn = findViewById(R.id.buttonLogin);
        buttonLogIn.setOnClickListener(v -> {
            buttonLogIn.setEnabled(false);
            buttonLogIn.setText("로그인중...");
            logInManager.KaKaoLogin();
        });

        // 초기화
        logInManager = new KakaoLogInManager(this);

        // 로그인 체크 콜백 구현
        logInManager.setCallBack(() -> UserApiClient.getInstance().me((user, throwable) -> {
            if (throwable != null) {
                Toast.makeText(KakaoLoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                buttonLogIn.setEnabled(true);
                buttonLogIn.setText("로그인");
            } else if (user != null) {
                Toast.makeText(KakaoLoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                UtilSharedPreference.setString(KakaoLoginActivity.this, SharedKey.USER_ID, Long.toString(user.getId()));
                UtilSharedPreference.setString(KakaoLoginActivity.this, SharedKey.USER_NAME, user.getKakaoAccount().getProfile().getNickname());
                UtilSharedPreference.setString(KakaoLoginActivity.this, SharedKey.USER_IMG_URL, user.getKakaoAccount().getProfile().getThumbnailImageUrl());

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            return null;
        }));

        CafeDatabaseInit.cafeDataInit(this);
    }
}