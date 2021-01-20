package RoomDatabase.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import RoomDatabase.Dao.CafeDao;
import RoomDatabase.Entity.CafeInfo;
import RoomDatabase.Entity.CoffeeInfo;


@Database(entities = {CafeInfo.class, CoffeeInfo.class}, version = 1, exportSchema = false)
public abstract class CafeDatabase extends RoomDatabase {
    private static volatile CafeDatabase INSTANCE;

    public abstract CafeDao getCafeDao();

    public static CafeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CafeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CafeDatabase.class, "CafeDatabase")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
