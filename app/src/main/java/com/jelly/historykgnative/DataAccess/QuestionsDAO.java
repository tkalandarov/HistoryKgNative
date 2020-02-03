package com.jelly.historykgnative.DataAccess;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jelly.historykgnative.Models.QuestionModel;

import java.util.List;

@Dao
public interface QuestionsDAO
{
    @Query("SELECT * FROM questions")
    List<QuestionModel> getAll();

    @Query("SELECT * FROM questions WHERE Id = :id")
    QuestionModel getById(long id);

    @Insert
    void insert(QuestionModel question);

    @Update
    void update(QuestionModel question);

    @Delete
    void delete(QuestionModel question);
}
