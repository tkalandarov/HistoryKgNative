package com.jelly.historykgnative.Core;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jelly.historykgnative.DataAccess.AppDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class App extends Application
{
    public static App instance;
    public static App getInstance()
    {
        return instance;
    }

    private AppDatabase database;
    public AppDatabase getDatabase()
    {
        return database;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                .allowMainThreadQueries()
                .build();

        //copyFile();
    }

    private void copyFile()
    {
        try
        {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            String currentDBPath =
                    getDatabasePath(AppDatabase.DBNAME).getAbsolutePath();
            String backupDBPath = AppDatabase.DBNAME;

            File currentDB = new File(currentDBPath);
            File backupDB = new File(sd, backupDBPath);

            if (currentDB.exists())
            {
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
