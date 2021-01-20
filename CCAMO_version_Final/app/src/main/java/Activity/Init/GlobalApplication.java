package Activity.Init;

import android.app.Application;
import com.kakao.sdk.common.KakaoSdk;
import Utility.UtilSharedPreference;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // KaKao SDK 초기화
        KakaoSdk.init(this, "321e3fec64fe5ba97624aaf18060f3d9");

        UtilSharedPreference.clear(this);
    }
}
