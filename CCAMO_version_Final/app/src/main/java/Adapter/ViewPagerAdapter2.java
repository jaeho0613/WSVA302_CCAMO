package Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import team.wsva302.ccamo.R;


public class ViewPagerAdapter2 extends PagerAdapter {

    private Context context = null;

    public ViewPagerAdapter2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;

        if (context != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_cafe_info_pager, container, false);

            ImageView imageView = view.findViewById(R.id.imageView);
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.brewing1);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    break;
                case 1:
                    imageView.setImageResource(R.drawable.brewing2);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    break;
                case 2:
                    imageView.setImageResource(R.drawable.brewing3);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    break;
                case 3:
                    imageView.setImageResource(R.drawable.brewing4);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    break;

            }
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}