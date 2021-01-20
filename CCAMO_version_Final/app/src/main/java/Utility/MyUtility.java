package Utility;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import team.wsva302.ccamo.R;

public class MyUtility {

    /**
     * <pre>{@code
     * [해쉬키 얻기]
     * 각 컴퓨터에서 테스트시 "키 해시" 값을 변경해야함.
     * 공용 카카오 아이디로 로그인을 하여 "키 해시" 부분 수정
     * }
     * </pre>
     *
     * @param context 'this' 매개변수로!
     */
    public static String getHashKey(Context context) {
        return com.kakao.sdk.common.util.Utility.INSTANCE.getKeyHash(context);
    }

    /**
     * @param message 프린트 메세지
     */
    public static void println(String message) {
        Log.e("Debug", message);
    }

    /**
     * @param message 프린트 메세지
     * @param throwable 에러 메세지               
     */
    public static void println(String message, Throwable throwable) {
        Log.e("Debug", message, throwable);
    }
}
