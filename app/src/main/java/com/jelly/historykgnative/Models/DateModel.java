package com.jelly.historykgnative.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Dates")
public class DateModel implements Serializable // implementing Serializable to be able to pass objects to other activities through Intent
{
    @PrimaryKey(autoGenerate = true) // Enable autoincrement
    public int Id;

    public int DatePos;
    public String DateText;

    public String EventName;
    public String EventText;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] Picture;
}
