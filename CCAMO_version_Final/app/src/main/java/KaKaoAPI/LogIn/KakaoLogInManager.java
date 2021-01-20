package KaKaoAPI.LogIn;

import android.content.Context;
import android.util.Log;

import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;

import Utility.MyUtility;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class KakaoLogInManager {

    Context context;
    KakaoLoginCallBack loginCallBack;

    public KakaoLogInManager(Context context) {
        this.context = context;
        loginCallBack = null;
    }

    public void setCallBack(KakaoLoginCallBack loginCallBack) {
        this.loginCallBack = loginCallBack;
    }

    // 콜백 정의
    private Function2<OAuthToken, Throwable, Unit> callBack = (oAuthToken, throwable) -> {
        if (oAuthToken != null) {
            MyUtility.println("AuthToKen : " + oAuthToken);
        }
        if (throwable != null) {
            MyUtility.println("Throwable : " + throwable);
        }
        loginCallBack.LoginCallBack();
        return null;
    };

    // 카카오 로그인
    public void KaKaoLogin() {
        if (LoginClient.getInstance().isKakaoTalkLoginAvailable(context)) {
            LoginClient.getInstance().loginWithKakaoTalk(context, callBack);
        } else {
            LoginClient.getInstance().loginWithKakaoAccount(context, callBack);
        }
    }

    public void KakaoLogOut() {
        UserApiClient.getInstance().logout(throwable -> {
            if (throwable != null) {
                MyUtility.println("로그아웃 실패, SDK에서 토큰 삭제됨", throwable);
            } else {
                MyUtility.println("AuthToKen : " + throwable);

                MyUtility.println("로그인 성공");
            }
            return null;
        });
    }
}
