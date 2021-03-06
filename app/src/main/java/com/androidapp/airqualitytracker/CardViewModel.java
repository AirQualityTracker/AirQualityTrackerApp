package com.androidapp.airqualitytracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
/***
 * This class represents a  model that manages all the cards contained in the repository
 */

public class CardViewModel extends AndroidViewModel {
    //card repo
    private final CardRepository repository;
    //list of all the cards on the repo
    private final LiveData<List<Card>> allCards;

    public CardViewModel(@NonNull Application application) {
        super(application);
        repository = new CardRepository(application);
        allCards = repository.getAllCards();
    }

    public void insert(Card card) {
        repository.insert(card);
    }

    public void update(Card card) {
        repository.update(card);
    }

    public void delete(Card card) {
        repository.delete(card);
    }

    public void deleteAllCards() {
        repository.deleteAllCards();
    }

    public LiveData<List<Card>> getAllCards() {
        return allCards;
    }
}
