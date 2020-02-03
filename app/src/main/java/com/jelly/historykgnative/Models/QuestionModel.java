package com.jelly.historykgnative.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Questions")
public class QuestionModel
{
    @PrimaryKey(autoGenerate = true) // Enable autoincrement
    public int Id;

    public String QuestionText;

    public String WrongAnswer1;
    public String WrongAnswer2;
    public String WrongAnswer3;
    public String WrongAnswer4;

    public String RightAnswer;
}
