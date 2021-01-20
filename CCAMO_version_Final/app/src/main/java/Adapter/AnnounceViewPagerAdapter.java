package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import team.wsva302.ccamo.R;

public class AnnounceViewPagerAdapter extends PagerAdapter {

    private Context context = null;

    public AnnounceViewPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;

        if (context != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_announce_view, container, false);

            ImageView imageView = view.findViewById(R.id.imageView);
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.notice1);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    break;
                case 1:
                    imageView.setImageResource(R.drawable.notice2);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    break;
                case 2:
                    imageView.setImageResource(R.drawable.notice3);
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
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
