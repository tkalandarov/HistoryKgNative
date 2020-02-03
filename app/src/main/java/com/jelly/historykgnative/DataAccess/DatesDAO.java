package com.jelly.historykgnative.DataAccess;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jelly.historykgnative.Models.DateModel;

import java.util.List;

@Dao
public interface DatesDAO
{
    @Query("SELECT * FROM dates")
    List<DateModel> getAll();

    @Query("SELECT * FROM dates WHERE Id = :id")
    DateModel getById(long id);

    @Insert
    void insert(DateModel date);

    @Update
    void update(DateModel date);

    @Delete
    void delete(DateModel date);
}
