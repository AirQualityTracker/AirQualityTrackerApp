package com.androidapp.airqualitytracker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * This class reprents the database that holds the cards
 * */

@SuppressWarnings("deprecation")
@Database(entities = Card.class, version = 7, exportSchema = false)
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

    private static final RoomDatabase.Callback roomCallback = new Callback() {
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
        private final CardDao cardDao;

        private PopulateDbAsyncTask(CardDatabase database) {
            cardDao = database.cardDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
