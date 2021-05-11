package com.androidapp.airqualitytracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/* The Data Access Object (DAO) is an interface that defines
 all the database operations we want to do on our entity.*/
@Dao
public interface CardDao {

    @Insert
    void insert(Card card);

    @Update
    void update(Card card);

    @Delete
    void delete(Card card);

    @Query("DELETE FROM card_table")
    void deleteAllCards();

    @Query("SELECT * FROM card_table ORDER BY id ASC")
    LiveData<List<Card>> getAllCards();
}
