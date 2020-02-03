package com.jelly.historykgnative.DataAccess;

import android.content.Context;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.RoomDatabase;

import com.jelly.historykgnative.Models.DateModel;
import com.jelly.historykgnative.Models.PersonModel;
import com.jelly.historykgnative.Models.QuestionModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Database(entities = {DateModel.class, PersonModel.class, QuestionModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract DatesDAO datesDAO();
    public abstract PeopleDAO peopleDAO();
    public abstract QuestionsDAO questionsDAO();

    public static final String DBNAME = "database.db";

    public AppDatabase()
    {
        super();
    }

    @Override
    public void init(@NonNull DatabaseConfiguration configuration)
    {
        // Comment next line to auto generate database template in device's internal storage
        importExistingDatabase(configuration.context, true); // Invokes the Import of the existing database.
        super.init(configuration);
    }

    private void importExistingDatabase(Context context, boolean throw_exception)
    {
        int buffer_size = 32768;
        File dbpath = context.getDatabasePath(DBNAME);
        if (dbpath.exists())
        {
            return; // Database already exists
        }
        // Just in case make the directories
        File dirs = new File(dbpath.getParent());
        dirs.mkdirs();
        int stage = 0;
        byte[] buffer = new byte[buffer_size];
        long total_bytes_read = 0;
        long total_bytes_written = 0;
        int bytes_read = 0;
        try
        {
            InputStream assetdb = context.getAssets().open(DBNAME);
            stage++;
            dbpath.createNewFile();
            stage++;
            OutputStream realdb = new FileOutputStream(dbpath);
            stage++;
            while ((bytes_read = assetdb.read(buffer)) > 0)
            {
                total_bytes_read = total_bytes_read + bytes_read;
                realdb.write(buffer, 0, bytes_read);
                total_bytes_written = total_bytes_written + bytes_read;
            }
            stage++;
            realdb.flush();
            stage++;
            assetdb.close();
            stage++;
            realdb.close();
            stage++;
        } catch (IOException e)
        {
            String failed_at = "";
            switch (stage)
            {
                case 0:
                    failed_at = "Opening Asset " + DBNAME;
                    break;
                case 1:
                    failed_at = "Creating Output Database " + dbpath.getAbsolutePath();
                    break;
                case 2:
                    failed_at = "Genreating Database OutputStream " + dbpath.getAbsolutePath();
                    break;
                case 3:
                    failed_at = "Copying Data from Asset Database to Output Database. " +
                            " Bytes read=" + String.valueOf(total_bytes_read) +
                            " Bytes written=" + String.valueOf(total_bytes_written);
                    break;
                case 4:
                    failed_at = "Flushing Written Data (" +
                            String.valueOf(total_bytes_written) +
                            " bytes written)";
                    break;
                case 5:
                    failed_at = "Closing Asset Database File.";
                    break;
                case 6:
                    failed_at = "Closing Created Database File.";
            }
            String msg = "An error was encountered copying the Database " +
                    "from the asset file to New Database. " +
                    "The error was encountered whilst :-\n\t" + failed_at;
            Log.e("IMPORTDATABASE", msg);
            e.printStackTrace();
            if (throw_exception)
            {
                throw new RuntimeException(msg);
            }
        }
    }
}
