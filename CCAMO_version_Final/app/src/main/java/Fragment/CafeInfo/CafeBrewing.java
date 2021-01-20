package Fragment.CafeInfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import Activity.PayActivity;
import Adapter.ViewPagerAdapter2;
import Fragment.Google.FragmentGoogleMap;
import RoomDatabase.CafeDatabaseHelper;
import RoomDatabase.Dao.CafeDao;
import RoomDatabase.Database.CafeDatabase;
import RoomDatabase.Entity.CafeInfo;
import RoomDatabase.Entity.CoffeeInfo;
import Utility.MyUtility;
import team.wsva302.ccamo.R;


public class CafeBrewing extends Fragment {

    boolean isMap = false;
    boolean isInit = false;
    FragmentGoogleMap fragmentGoogleMap;
    CafeDao cafeDao;

    double lat;
    double lng;

    List<CoffeeInfo> coffeeInfoList;
    List<String> coffeeNames;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafe_brewing, container, false);

        ViewPagerAdapter2 adapter2 = new ViewPagerAdapter2(getContext());
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter2);

        cafeDao = CafeDatabase.getDatabase(getContext()).getCafeDao();
        
        CafeInfo cafeInfo = cafeDao.getCafe("브루잉세레모");
        lat = cafeInfo.getLat();
        lng = cafeInfo.getLng();

        fragmentGoogleMap = new FragmentGoogleMap(lat, lng, "브루잉세레모");
        ScrollView scrollView = view.findViewById(R.id.scrollView);

        // 맵 뷰 버튼
        Button buttonMap = view.findViewById(R.id.cafeMap);
        buttonMap.setOnClickListener(v -> {
            try {
                if (!isMap) {
                    isMap = true;
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frameView, fragmentGoogleMap).commit();
                    buttonMap.setText("지도 닫기");

                    scrollView.setEnabled(true);
                    
                    MyUtility.println("브루 스크롤");

                } else {
                    isMap = false;
                    getActivity().getSupportFragmentManager().beginTransaction().remove(fragmentGoogleMap).commit();
                    buttonMap.setText("위치");
                }
            } catch (Exception e) {
                MyUtility.println(e.toString());
            }
        });


        coffeeInfoList = cafeDao.getCoffee("브루잉세레모");
        coffeeNames = new ArrayList<>();

        for (CoffeeInfo name : coffeeInfoList) {
            coffeeNames.add(name.getCoffeeName());
        }

        Spinner spinner = view.findViewById(R.id.coffeeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, coffeeNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isInit) {
                    isInit = true;
                } else {
                    MyUtility.println("getCoffeeName : " + coffeeInfoList.get(position).getCoffeeName());
                    MyUtility.println("getPrice : " + coffeeInfoList.get(position).getPrice());

                    PayActivity payActivity = new PayActivity(
                            coffeeInfoList.get(position).getCoffeeName(),
                            coffeeInfoList.get(position).getPrice());

                    Intent intent = new Intent(getContext(), payActivity.getClass());
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }
}