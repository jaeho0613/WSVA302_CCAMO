package RoomDatabase.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "cafe")
public class CafeInfo {

    @PrimaryKey
    @NonNull
    private String cafeName; // 카페 이름
    private String cafeContent; // 카페 설명
    private Double lat; // 위도
    private Double lng; // 경도
    private String payId; // kakao Pay Id

    @Ignore
    private List<CoffeeInfo> coffeeInfoList = null;

    public CafeInfo() {
    }

    @Ignore
    public CafeInfo(String cafeName, String cafeContent, Double lat, Double lng, String payId, List<CoffeeInfo> coffeeInfoList) {
        super();
        this.cafeName = cafeName;
        this.cafeContent = cafeContent;
        this.lat = lat;
        this.lng = lng;
        this.payId = payId;
        this.coffeeInfoList = coffeeInfoList;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lon) {
        this.lng = lon;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public List<CoffeeInfo> getCoffeeInfoList() {
        return coffeeInfoList;
    }

    public void setCoffeeInfoList(List<CoffeeInfo> coffeeInfoList) {
        this.coffeeInfoList = coffeeInfoList;
    }

    public String getCafeContent() {
        return cafeContent;
    }

    public void setCafeContent(String cafeContent) {
        this.cafeContent = cafeContent;
    }

    @NonNull
    @Override
    public String toString() {
        return "CafeInfo{" +
                "id" + cafeName + "\n" +
                "id" + cafeContent + "\n" +
                "id" + lat + "\n" +
                "id" + lng + "\n" +
                "id" + payId;
    }
}
