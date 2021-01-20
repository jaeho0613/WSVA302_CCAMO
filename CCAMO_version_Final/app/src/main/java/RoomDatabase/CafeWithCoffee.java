package RoomDatabase;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import RoomDatabase.Entity.CafeInfo;
import RoomDatabase.Entity.CoffeeInfo;


public class CafeWithCoffee {

    public CafeWithCoffee() {
    }

    @Embedded
    public CafeInfo cafeInfo;

    @Relation(parentColumn = "cafeName", entityColumn = "cafeName", entity = CafeInfo.class)
    private List<CoffeeInfo> coffeeList;

    public CafeInfo getCafeInfo() {
        return cafeInfo;
    }

    public void setCafeInfo(CafeInfo cafeInfo) {
        this.cafeInfo = cafeInfo;
    }

    public List<CoffeeInfo> getCoffeeList() {
        return coffeeList;
    }

    public void setCoffeeList(List<CoffeeInfo> coffeeList) {
        this.coffeeList = coffeeList;
    }
}
