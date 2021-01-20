package Fragment.Google;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.load.data.AssetFileDescriptorLocalUriFetcher;

import java.util.ArrayList;
import java.util.List;

import RoomDatabase.Database.CafeDatabase;
import RoomDatabase.Entity.CafeInfo;
import team.wsva302.ccamo.R;

public class GooglePin extends AppCompatActivity {

    FragmentGoogleMap googleMap;
    CafeDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_pin);

        database = CafeDatabase.getDatabase(this);

        List<CafeInfo> cafeInfoList = database.getCafeDao().getAllCafe();
        List<Double> lats = new ArrayList<>();
        List<Double> lngs = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        for (CafeInfo item: cafeInfoList) {
            lats.add(item.getLat());
            lngs.add(item.getLng());
            titles.add(item.getCafeName());
        }

        googleMap = new FragmentGoogleMap(lats, lngs, titles);
        getSupportFragmentManager().beginTransaction().replace(R.id.googleMap, googleMap).commit();
    }
}