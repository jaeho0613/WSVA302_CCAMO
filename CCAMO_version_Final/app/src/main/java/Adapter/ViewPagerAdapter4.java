package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import team.wsva302.ccamo.R;


public class ViewPagerAdapter4 extends PagerAdapter {

    private Context context = null;

    public ViewPagerAdapter4(Context context) {
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
                    imageView.setImageResource(R.drawable.rudiment1);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    break;
                case 1:
                    imageView.setImageResource(R.drawable.rudiment2);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    break;
                case 2:
                    imageView.setImageResource(R.drawable.rudiment3);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    break;
                case 3:
                    imageView.setImageResource(R.drawable.rudiment4);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    break;
                case 4:
                    imageView.setImageResource(R.drawable.rudiment5);
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