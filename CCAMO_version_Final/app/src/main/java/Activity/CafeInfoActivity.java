package Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import Utility.MyUtility;
import team.wsva302.ccamo.R;

public class CafeInfoActivity extends AppCompatActivity {

    // 카페 메뉴 필드
    static Fragment cafeList1;

    public CafeInfoActivity() {
    }

    public CafeInfoActivity(Fragment cafeList1) {
        super();
        CafeInfoActivity.cafeList1 = cafeList1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_info);

        getSupportFragmentManager().beginTransaction().replace(R.id.cafeInfo, cafeList1).commit();
    }
}