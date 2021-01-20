package RoomDatabase.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "coffee")
public class CoffeeInfo {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ForeignKey(
            entity = CafeInfo.class,
            parentColumns = "id",
            childColumns = "coffeeId",
            onDelete = CASCADE
    )
    private String cafeId;

    private String coffeeName; // 커피 이름
    private String price; // 커피 가격

    public CoffeeInfo() {
    }

    @Ignore
    public CoffeeInfo(String coffeeName, String price) {
        super();
        this.coffeeName = coffeeName;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCafeId() {
        return cafeId;
    }

    public void setCafeId(String cafeId) {
        this.cafeId = cafeId;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
