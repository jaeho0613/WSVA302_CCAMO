package RoomDatabase;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.List;

import RoomDatabase.Dao.CafeDao;
import RoomDatabase.Database.CafeDatabase;
import RoomDatabase.Entity.CafeInfo;
import RoomDatabase.Entity.CoffeeInfo;


@RequiresApi(api = Build.VERSION_CODES.N)
public class CafeDatabaseHelper {
    private CafeDao cafeDao;

    public CafeDatabaseHelper(CafeDatabase cafeDatabase) {
        cafeDao = cafeDatabase.getCafeDao();
    }

    public void saveCafe(CafeInfo cafeInfo) {
        cafeDao.saveCafe(cafeInfo);
        String cafeName = cafeInfo.getCafeName();
        saveCoffee(cafeName, cafeInfo.getCoffeeInfoList());
    }

    public void saveCafes(CafeInfo... cafeInfos) {
        for (CafeInfo info : cafeInfos) {
            cafeDao.saveCafe(info);
            String cafeName = info.getCafeName();
            saveCoffee(cafeName, info.getCoffeeInfoList());
        }
    }

    private void saveCoffee(String cafeName, List<CoffeeInfo> coffeeInfoList) {
        List<CoffeeInfo> coffeeInfos = cafeDao.getCoffee(cafeName);

        if (coffeeInfos.size() == 0) {
            insertCafeWithCoffee(cafeName, coffeeInfoList);
            cafeDao.saveCoffee(coffeeInfoList);
            Log.e("Debug", "추가");
        } else {
            Log.e("Debug", "추가 안됨");
        }
    }

    private void insertCafeWithCoffee(@NonNull String cafeName, List<CoffeeInfo> coffeeInfoList) {
        coffeeInfoList.forEach(coffeeInfo -> coffeeInfo.setCafeId(cafeName));
    }
}

