package com.androidapp.airqualitytracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/* The Repository is a simple Java class that abstracts the data layer
 from the rest of the app and mediates between different data sources.*/
// FIXME: Replace Deprecated Methods
@SuppressWarnings("deprecation")
public class CardRepository {
    private final CardDao cardDao;
    private final LiveData<List<Card>> allCards;

    public CardRepository(Application application) {
        CardDatabase database = CardDatabase.getInstance(application);
        cardDao = database.cardDao();
        allCards = cardDao.getAllCards();
    }

    public void insert(Card card) {
        new InsertCardAsyncTask(cardDao).execute(card);
    }

    public void update(Card card) {
        new UpdateCardAsyncTask(cardDao).execute(card);
    }

    public void delete(Card card) {
        new DeleteCardAsyncTask(cardDao).execute(card);
    }

    public void deleteAllCards() {
        new DeleteAllCardsAsyncTask(cardDao).execute();
    }

    public LiveData<List<Card>> getAllCards() {
        return allCards;
    }

    /* Room doesn't allow database queries on the main thread,
     we use AsyncTasks to execute them asynchronously. */
    private static class InsertCardAsyncTask extends AsyncTask<Card, Void, Void> {
        private final CardDao cardDao;

        private InsertCardAsyncTask(CardDao cardDao) {
            this.cardDao = cardDao;
        }

        @Override
        protected Void doInBackground(Card... cards) {
            cardDao.insert(cards[0]);
            return null;
        }
    }

    private static class UpdateCardAsyncTask extends AsyncTask<Card, Void, Void> {
        private final CardDao cardDao;

        private UpdateCardAsyncTask(CardDao cardDao) {
            this.cardDao = cardDao;
        }

        @Override
        protected Void doInBackground(Card... cards) {
            cardDao.update(cards[0]);
            return null;
        }
    }

    private static class DeleteCardAsyncTask extends AsyncTask<Card, Void, Void> {
        private final CardDao cardDao;

        private DeleteCardAsyncTask(CardDao cardDao) {
            this.cardDao = cardDao;
        }

        @Override
        protected Void doInBackground(Card... cards) {
            cardDao.delete(cards[0]);
            return null;
        }
    }

    private static class DeleteAllCardsAsyncTask extends AsyncTask<Void, Void, Void> {
        private final CardDao cardDao;

        private DeleteAllCardsAsyncTask(CardDao cardDao) {
            this.cardDao = cardDao;
        }

        @Override
        protected Void doInBackground(Void... Void) {
            cardDao.deleteAllCards();
            return null;
        }

    }
}
