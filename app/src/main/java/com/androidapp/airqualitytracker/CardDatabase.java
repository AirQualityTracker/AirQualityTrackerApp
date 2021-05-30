package com.androidapp.airqualitytracker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* The RoomDatabase is an abstract class that ties all the pieces together
 and connects the entities to their corresponding DAO.*/
// FIXME: Replace Deprecated Methods
//@SuppressWarnings("deprecation")
@Database(entities = Card.class, version = 5, exportSchema = false)
public abstract class CardDatabase extends RoomDatabase {

    private static CardDatabase INSTANCE;

    public abstract CardDao cardDao();

    // Only one thread at a time can access this method.
    public static synchronized CardDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CardDatabase.class, "card_database")
                    .fallbackToDestructiveMigration() // To recreate database if version increase.
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }

        // FIXME: Remove before production
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CardDao cardDao;

        private PopulateDbAsyncTask(CardDatabase database) {
            cardDao = database.cardDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cardDao.deleteAllCards();
            cardDao.insert(new Card(
                    "Thessaloniki",
                    "Central Macedonia",
                    "Greece",
                    45,
                    20,
                    23,
                    20,
                    50,
                    0,
                    0
            ));
            cardDao.insert(new Card(
                    "Los Angeles",
                    "California",
                    "USA",
                    76,
                    20,
                    15,
                    30,
                    43,
                    0,
                    0
            ));
            cardDao.insert(new Card(
                    "Sonota",
                    "Oita",
                    "Japan",
                    112,
                    20,
                    19,
                    40,
                    57,
                    0,
                    0
            ));
            cardDao.insert(new Card(
                    "Port Harcourt",
                    "Rivers",
                    "Nigeria",
                    196,
                    20,
                    32,
                    30,
                    65,
                    0,
                    0
            ));
            cardDao.insert(new Card(
                    "St. John's",
                    "Newfoundland",
                    "Canada",
                    214,
                    20,
                    26,
                    25,
                    39,
                    0,
                    0
            ));
            cardDao.insert(new Card(
                    "Beijing",
                    "Beijing",
                    "China",
                    288,
                    20,
                    29,
                    15,
                    44,
                    0,
                    0
            ));

            return null;
        }
    }
}
