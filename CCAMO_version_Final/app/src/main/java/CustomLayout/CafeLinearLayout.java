package CustomLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import team.wsva302.ccamo.R;

// 카페 메뉴 리니어 레이아웃
public class CafeLinearLayout extends LinearLayout {

    public CafeLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public CafeLinearLayout(Context context) {
        super(context);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_cafe_list, this, true);
    }
}
