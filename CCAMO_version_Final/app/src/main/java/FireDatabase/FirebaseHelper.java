package FireDatabase;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Utility.MyUtility;
import Utility.SharedKey;
import Utility.UtilSharedPreference;

public class FirebaseHelper {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseRef;
    FirebaseCallBack firebaseCallBack;

    public void setCallBack(FirebaseCallBack firebaseCallBack) {
        this.firebaseCallBack = firebaseCallBack;
    }

    public FirebaseHelper(@NonNull Context context, String pointRef) {
        this.databaseRef = database.getReference(pointRef);
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MyUtility.println("Database onDataChange Key : " + snapshot.getKey());
                MyUtility.println("Database onDataChange Value : " + snapshot.getValue());
                if (snapshot.getKey() != null && snapshot.getValue() != null) {
                    UtilSharedPreference.setInteger(context, SharedKey.USER_POINT, Integer.parseInt(snapshot.getValue().toString()));
                    if (firebaseCallBack != null) {
                        firebaseCallBack.FirebaseInit();
                    }
                } else {
                    UtilSharedPreference.setInteger(context, SharedKey.USER_POINT, 0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                MyUtility.println("Database onCancelled : " + error.toString());
            }
        });
    }

    public void WriteDatabase(Integer point) {
        databaseRef.setValue(point);
    }
}
