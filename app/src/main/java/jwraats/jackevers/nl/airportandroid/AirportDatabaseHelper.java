package jwraats.jackevers.nl.airportandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by jack on 20-10-2015.
 */
public class AirportDatabaseHelper extends SQLiteAssetHelper{

    private static AirportDatabaseHelper instance;

    private static final String DATABASE_NAME = "airports.sqlite";
    private static final int DATABASE_VERSION = 1;

    private AirportDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // private to prevent objectification of this class.
        // objectification is bad!
    }

    public static AirportDatabaseHelper getInstance(Context context){
        if(null == instance)
        {
            instance = new AirportDatabaseHelper(context);
        }

        return instance;
    }

    public Cursor getAirports(){
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT icao, name FROM airports WHERE iso_country = \"NL\"";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        return c;
    }

}
