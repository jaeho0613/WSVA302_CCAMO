package RoomDatabase;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import RoomDatabase.Database.CafeDatabase;
import RoomDatabase.Entity.CafeInfo;
import RoomDatabase.Entity.CoffeeInfo;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CafeDatabaseInit {
    public static void cafeDataInit(Context context) {
        CafeDatabaseHelper helper = new CafeDatabaseHelper(CafeDatabase.getDatabase(context));

//--------------------------------------연무장 셋---------------------------------------//

        List<CoffeeInfo> yeonmujangCoffeeList = new ArrayList<>(Arrays.asList(
                new CoffeeInfo("아메리카노", "4500"),
                new CoffeeInfo("라떼", "5000"),
                new CoffeeInfo("크림라떼", "6000"),
                new CoffeeInfo("플랫화이트", "5000"),
                new CoffeeInfo("아포가토", "6500"),
                new CoffeeInfo("카바레", "6000"),
                new CoffeeInfo("초코라떼", "6500"),
                new CoffeeInfo("아이스크림", "6500"),
                new CoffeeInfo("피치우롱", "5000"),
                new CoffeeInfo("후르츠드림", "5000"),
                new CoffeeInfo("루이보스 빌베리", "5000"),
                new CoffeeInfo("노을(한라봉 베이스)", "6500"),
                new CoffeeInfo("햇볕(레몬 베이스)", "6500"),
                new CoffeeInfo("티라미수", "9000"),
                new CoffeeInfo("치즈 케이크", "6500"),
                new CoffeeInfo("라즈베리 쇼콜라", "6500"),
                new CoffeeInfo("토스트", "6500")
        ));

        CafeInfo yeonmujangCafeInfo = new CafeInfo("연무장",
                "성수가 한눈에 보이고 탁 트인 뷰가 매력적인 루프탑 카페",
                37.5430519701056,
                127.05326569988225,
                "1001",
                yeonmujangCoffeeList
        );

//-------------------------------------우디 셋-----------------------------------------//

        List<CoffeeInfo> woodyCoffeeList = new ArrayList<>(Arrays.asList(
                new CoffeeInfo("아메리카노", "4500"),
                new CoffeeInfo("라떼", "5000"),
                new CoffeeInfo("바닐라라떼", "5500"),
                new CoffeeInfo("플랫화이트", "5000"),
                new CoffeeInfo("우디카페인", "5500"),
                new CoffeeInfo("청포도에이드", "6000"),
                new CoffeeInfo("초록녹차 라떼", "6000"),
                new CoffeeInfo("마르코폴로", "5500"),
                new CoffeeInfo("웨딩임페리얼", "5500"),
                new CoffeeInfo("우디 꿀단지", "5500"),
                new CoffeeInfo("루이보스 테린느", "5500"),
                new CoffeeInfo("말차 모나카-앙", "4500"),
                new CoffeeInfo("모나카-앙", "4500")
        ));

        CafeInfo woodyCafeInfo = new CafeInfo("우디",
                "골목에 숨겨진 감성적인 인테리어의 카페",
                37.5375768,
                127.050931,
                "1002",
                woodyCoffeeList
        );
//-----------------------------------브루잉세레모 셋-----------------------------------------//

        List<CoffeeInfo> brewingCoffeeList = new ArrayList<>(Arrays.asList(
                new CoffeeInfo("brewing(Hot)", "5000"),
                new CoffeeInfo("brewing(Iced)", "6000"),
                new CoffeeInfo("White", "5000"),
                new CoffeeInfo("Vienna", "5500"),
                new CoffeeInfo("Affogato", "6000"),
                new CoffeeInfo("Grape Fruit", "5000"),
                new CoffeeInfo("Lemon", "5000"),
                new CoffeeInfo("Orange", "5000"),
                new CoffeeInfo("Black Tea", "5000")
        ));

        CafeInfo brewingCafeInfo = new CafeInfo("브루잉세레모",
                "취향에 맞는 원두로 즐기는 핸드드립커피가 있는 카페",
                37.543394563045176,
                127.05509045895762,
                "1003",
                brewingCoffeeList
        );
//----------------------------------------------루디먼 셋--------------------------------//

        List<CoffeeInfo> rudimentCoffeeList = new ArrayList<>(Arrays.asList(
                new CoffeeInfo("아메리카노", "4500"),
                new CoffeeInfo("플랫화이트", "4800"),
                new CoffeeInfo("카푸치노", "4800"),
                new CoffeeInfo("라떼", "5000"),
                new CoffeeInfo("바닐라라떼", "5500"),
                new CoffeeInfo("캐러멀라떼", "5500"),
                new CoffeeInfo("크림라떼", "6000"),
                new CoffeeInfo("호지차", "4500"),
                new CoffeeInfo("호지밀크티", "6000"),
                new CoffeeInfo("자몽에이드", "6000"),
                new CoffeeInfo("자몽차", "6000"),
                new CoffeeInfo("레몬차", "6000"),
                new CoffeeInfo("발로나초코", "6500"),
                new CoffeeInfo("하우스와인", "8000"),
                new CoffeeInfo("녹차테린느", "2000"),
                new CoffeeInfo("바닐라생크림 추가", "500"),
                new CoffeeInfo("플레인스콘", "4000"),
                new CoffeeInfo("잼스콘", "4500"),
                new CoffeeInfo("발로나초코스콘", "5000")
        ));

        CafeInfo rudimentCafeInfo = new CafeInfo("루디먼",
                "디자이너들이 모여 운영하는 주택 개조 카페",
                37.546827322183894,
                127.05445600361972,
                "1004",
                rudimentCoffeeList
        );

//--------------------------------------트콜렉트------------------------------------//

        List<CoffeeInfo> collectCoffeeList = new ArrayList<>(Arrays.asList(
                new CoffeeInfo("아메리카노", "5000"),
                new CoffeeInfo("카페라떼", "55"),
                new CoffeeInfo("바닐라라떼", "6000"),
                new CoffeeInfo("헤이즐넛라떼", "6000"),
                new CoffeeInfo("콜드브루", "5000"),
                new CoffeeInfo("바닐라크림 콜드브루", "5000"),
                new CoffeeInfo("페션후르츠 차", "6500"),
                new CoffeeInfo("페션후르츠 에이드", "6000"),
                new CoffeeInfo("큐브 파운드 케이크", "3800"),
                new CoffeeInfo("COFFEE Beans 200g", "18000")
        ));

        CafeInfo collectCafeInfo = new CafeInfo("트콜렉트",
                "매력적인 바 테이블에서 맛있는 커피를 즐길 수 있는 카페",
                37.54702759467661,
                127.04076367599896,
                "1005",
                collectCoffeeList
        );

//----------------------------------------------------------------------------------//

        helper.saveCafes(woodyCafeInfo, yeonmujangCafeInfo, rudimentCafeInfo, brewingCafeInfo, collectCafeInfo);
    }
}
