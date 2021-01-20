package RoomDatabase.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import RoomDatabase.Entity.CafeInfo;
import RoomDatabase.Entity.CoffeeInfo;

@Dao
public interface CafeDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveCafe(CafeInfo cafeInfo);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveCoffee(List<CoffeeInfo> coffeeList);

    @Transaction
    @Query("SELECT * FROM cafe WHERE cafeName = :cafeName")
    CafeInfo getCafe(String cafeName);

    @Transaction
    @Query("SELECT * FROM coffee WHERE cafeId = :cafeId")
    List<CoffeeInfo> getCoffee(String cafeId);

    @Transaction
    @Query("SELECT * FROM cafe")
    List<CafeInfo> getAllCafe();
}
