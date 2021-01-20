package Fragment.Google;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import team.wsva302.ccamo.R;

public class FragmentGoogleMap extends Fragment implements OnMapReadyCallback {

    FrameLayout googleFrameLayout;
    GoogleMap googleMap;

//    String title;
//    double lat;
//    double lng;

    List<String> titles = new ArrayList<>();
    List<Double> lats = new ArrayList<>();
    List<Double> lngs = new ArrayList<>();

    public FragmentGoogleMap(double lat, double lng, String title) {
        lats.add(lat);
        lngs.add(lng);
        titles.add(title);
    }

    public FragmentGoogleMap(List<Double> lats, List<Double> lngs, List<String> titles) {
        this.lats = lats;
        this.lngs = lngs;
        this.titles = titles;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_google_map, container, false);

        googleFrameLayout = view.findViewById(R.id.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;

        for (int i = 0; i < titles.size(); i++) {
            MapMark(lats.get(i), lngs.get(i), titles.get(i));
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lats.get(0), lngs.get(0))));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(14));
    }

    public void MapMark(double lat, double lng, String title) {

        LatLng latLng = new LatLng(lat, lng);

        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(title);
        googleMap.addMarker(markerOptions);
    }
}