package Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Adapter.Notice.NoticeActivity;
import CustomLayout.CafeLinearLayout;
import Adapter.AnnounceViewPagerAdapter;
import FireDatabase.FirebaseCallBack;
import FireDatabase.FirebaseHelper;
import Fragment.CafeInfo.CafeWody;
import Fragment.CafeInfo.CafeBrewing;
import Fragment.CafeInfo.CafeYeonmujang;
import Fragment.CafeInfo.CafeRudiment;
import Fragment.CafeInfo.CafeCollect;
import Fragment.Google.GooglePin;
import Utility.MyUtility;
import Utility.SharedKey;
import Utility.UtilGlide;
import Utility.UtilSharedPreference;
import team.wsva302.ccamo.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // ViewPager 필드
    ViewPager viewPager;
    AnnounceViewPagerAdapter pagerAdapter;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;

    // Drawer 필드
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View headerView;

    // 중단 카페 메뉴 레이아웃
    LinearLayout cafeListLayout;
    List<Integer> cafeImages;

    // 파이어 베이스
    FirebaseHelper helper;

    // 사용자 UI
    TextView userText;
    TextView userPoint;
    ImageView userImage;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

        MyUtility.println("onResume -> 가져온 " + UtilSharedPreference.getInteger(this, SharedKey.USER_POINT));
        userPoint.setText(UtilSharedPreference.getInteger(this, SharedKey.USER_POINT) + " P");
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyUtility.println("메인 크리에이트");

        // 파이어 베이스 초기화
        helper = new FirebaseHelper(getApplicationContext(), UtilSharedPreference.getString(getApplicationContext(), SharedKey.USER_ID));
        helper.setCallBack(new FirebaseCallBack() {
            @Override
            public void FirebaseInit() {
                MyUtility.println("callback -> 가져온 " + UtilSharedPreference.getInteger(getApplicationContext(), SharedKey.USER_POINT));
                userPoint.setText(UtilSharedPreference.getInteger(getApplicationContext(), SharedKey.USER_POINT) + "P");
            }
        });

        // Auto View Pager 설정
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new AnnounceViewPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == 3) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        // Drawer 필드 초기화
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 메뉴바 토글 연결
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 네비게이션
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Navigation 로그인 셋팅
        headerView = navigationView.getHeaderView(0);

        // - 사용자 이미지 셋팅
        userImage = headerView.findViewById(R.id.userImage);
        if (UtilSharedPreference.getString(this, SharedKey.USER_IMG_URL) != null) {
            UtilGlide.circleImage(this, UtilSharedPreference.getString(this, SharedKey.USER_IMG_URL), userImage);
        }

        // - 사용자 이름 셋팅
        userText = headerView.findViewById(R.id.userName);
        userText.setText(UtilSharedPreference.getString(this, SharedKey.USER_NAME));

        // - 사용자 포인트
        userPoint = headerView.findViewById(R.id.userPoint);
        MyUtility.println("onCreate -> 가져온 " + UtilSharedPreference.getInteger(this, SharedKey.USER_POINT));
        userPoint.setText(UtilSharedPreference.getInteger(this, SharedKey.USER_POINT) + "P");

        // 카페 목록 동적 추가
        cafeListLayout = findViewById(R.id.cafeListLayout);
        cafeImages = new ArrayList<>(Arrays.asList(R.drawable.viewpager1,
                R.drawable.viewpager2,
                R.drawable.viewpager3,
                R.drawable.viewpager4,
                R.drawable.viewpager5));

        // - 각 레이아웃 클릭, 이미지 로직
        for (int i = 0; i < 5; i++) {
            CafeLinearLayout cafeItemLayout = new CafeLinearLayout(getApplicationContext());
            cafeItemLayout.setOnClickListener(clickInLinearLayout(i));
            cafeItemLayout.setElevation(10);

            ImageView imageView = cafeItemLayout.findViewById(R.id.cafeImage);

            UtilGlide.roundImage(this, cafeImages.get(i), imageView, 30);

            cafeListLayout.addView(cafeItemLayout);
        }
    }

    // 카페 메뉴 클릭 리스너
    private View.OnClickListener clickInLinearLayout(int position) {
        return v -> {
            switch (position) {
                case 0:
                    CafeInfoActivity cafeInfoActivity1 = new CafeInfoActivity(new CafeWody());
                    Intent intent1 = new Intent(this, cafeInfoActivity1.getClass());
                    startActivity(intent1);
                    break;
                case 1:
                    CafeInfoActivity cafeInfoActivity2 = new CafeInfoActivity(new CafeBrewing());
                    Intent intent2 = new Intent(this, cafeInfoActivity2.getClass());
                    startActivity(intent2);
                    break;
                case 2:
                    CafeInfoActivity cafeInfoActivity3 = new CafeInfoActivity(new CafeYeonmujang());
                    Intent intent3 = new Intent(this, cafeInfoActivity3.getClass());
                    startActivity(intent3);
                    break;
                case 3:
                    CafeInfoActivity cafeInfoActivity4 = new CafeInfoActivity(new CafeRudiment());
                    Intent intent4 = new Intent(this, cafeInfoActivity4.getClass());
                    startActivity(intent4);
                    break;
                case 4:
                    CafeInfoActivity cafeInfoActivity5 = new CafeInfoActivity(new CafeCollect());
                    Intent intent5 = new Intent(this, cafeInfoActivity5.getClass());
                    startActivity(intent5);
                    break;
            }
        };
    }

    // 네비게이션 아이템 클릭 이벤트
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        MyUtility.println("네비게이션 클릭");
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_notice:
                Intent notice = new Intent(this, NoticeActivity.class);
                startActivity(notice);
                break;
            case R.id.menu_findCafe:
                Intent pin = new Intent(this, GooglePin.class);
                startActivity(pin);
                break;
            case R.id.menu_sendMessage:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("plain/text");

                // email setting 배열로 해놔서 복수 발송 가능
                String[] address = {"wsva302@gmail.com"};
                email.putExtra(Intent.EXTRA_EMAIL, address);
                email.putExtra(Intent.EXTRA_SUBJECT, "보내질 email 제목");
                email.putExtra(Intent.EXTRA_TEXT, "보낼 email 내용을 미리 적어 놓을 수 있습니다.\n");
                startActivity(email);
                break;
            case R.id.menu_logOut:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // 뒤로 가기 이벤트
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
