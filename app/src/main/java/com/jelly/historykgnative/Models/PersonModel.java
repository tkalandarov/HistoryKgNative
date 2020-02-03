package com.jelly.historykgnative.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "People")
public class PersonModel implements Serializable
{
    @PrimaryKey(autoGenerate = true) // Enable autoincrement
    public int Id;

    public String Name;

    public int YearPos;
    public String YearsOfLife;

    public String Text;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] Picture;
}
