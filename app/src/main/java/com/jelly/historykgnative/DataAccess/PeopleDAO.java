package com.jelly.historykgnative.DataAccess;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jelly.historykgnative.Models.PersonModel;

import java.util.List;

@Dao
public interface PeopleDAO
{
    @Query("SELECT * FROM people")
    List<PersonModel> getAll();

    @Query("SELECT * FROM people WHERE Id = :id")
    PersonModel getById(long id);

    @Insert
    void insert(PersonModel person);

    @Update
    void update(PersonModel person);

    @Delete
    void delete(PersonModel person);
}
