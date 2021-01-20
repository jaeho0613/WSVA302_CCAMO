package Fragment.CafeInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import Activity.PayActivity;
import Adapter.ViewPagerAdapter5;
import Fragment.Google.FragmentGoogleMap;
import RoomDatabase.Dao.CafeDao;
import RoomDatabase.Database.CafeDatabase;
import RoomDatabase.Entity.CafeInfo;
import RoomDatabase.Entity.CoffeeInfo;
import Utility.MyUtility;
import team.wsva302.ccamo.R;

public class CafeCollect extends Fragment {

    boolean isMap = false;
    boolean isInit = false;
    FragmentGoogleMap fragmentGoogleMap;
    CafeDao cafeDao;

    double lat;
    double lng;

    List<CoffeeInfo> coffeeInfoList;
    List<String> coffeeNames;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafe_collect, container, false);

        ViewPager viewPager = view.findViewById(R.id.viewPager);
        ViewPagerAdapter5 adapter5 = new ViewPagerAdapter5(getContext());
        viewPager.setAdapter(adapter5);

        cafeDao = CafeDatabase.getDatabase(getContext()).getCafeDao();
        CafeInfo cafeInfo = cafeDao.getCafe("트콜렉트");
        lat = cafeInfo.getLat();
        lng = cafeInfo.getLng();

        fragmentGoogleMap = new FragmentGoogleMap(lat, lng, "트콜렉트");


        Button button = view.findViewById(R.id.cafeMap);
        button.setOnClickListener(v -> {
            try {
                if (!isMap) {
                    isMap = true;
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frameView, fragmentGoogleMap).commit();
                    button.setText("지도 닫기");

                } else {
                    isMap = false;
                    getActivity().getSupportFragmentManager().beginTransaction().remove(fragmentGoogleMap).commit();
                    button.setText("위치");
                }
            } catch (Exception e) {
                MyUtility.println(e.toString());
            }
        });

        coffeeInfoList = cafeDao.getCoffee("트콜렉트");
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