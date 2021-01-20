package Utility;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import team.wsva302.ccamo.R;

public class UtilGlide {

    /**
     * @param context   생성 컨텍스트
     * @param file      사용할 이미지 (int형)
     * @param imageView 이미지를 넣을 뷰
     */
    public static void circleImage(Context context, int file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .circleCrop()
                .into(imageView);
    }

    /**
     * @param context   생성 컨텍스트
     * @param url       사용할 이미지 (url형)
     * @param imageView 이미지를 넣을 뷰
     */
    public static void circleImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .circleCrop()
                .into(imageView);
    }

    /**
     * @param context    생성 컨텍스트
     * @param file       사용할 이미지 (int형)
     * @param imageView  이미지를 넣을 뷰
     * @param roundCount 이미지 라운드 값
     */
    public static void roundImage(Context context, int file, ImageView imageView, int roundCount) {
        Glide.with(context)
                .load(file)
                .transform(new CenterCrop(), new RoundedCorners(roundCount))
                .into(imageView);
    }

    /**
     * @param context    생성 컨텍스트
     * @param url        사용할 이미지 (url형)
     * @param imageView  이미지를 넣을 뷰
     * @param roundCount 이미지 라운드 값
     */
    public static void roundImage(Context context, String url, ImageView imageView, int roundCount) {
        Glide.with(context)
                .load(url)
                .transform(new CenterCrop(), new RoundedCorners(roundCount))
                .into(imageView);
    }
}
