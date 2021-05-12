package Activity.Init;

import android.app.Application;
import android.util.Log;

import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.util.Utility;

import Utility.UtilSharedPreference;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("Debug",Utility.INSTANCE.getKeyHash(this));

        // KaKao SDK 초기화
        KakaoSdk.init(this, "9b14532b461e5b55ed4db2f3f8f15f3a");

        UtilSharedPreference.clear(this);
    }
}
