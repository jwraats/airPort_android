package jwraats.jackevers.nl.airportandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by jack on 20-10-2015.
 */
public class AirportDatabaseHelper extends SQLiteAssetHelper{

    private static AirportDatabaseHelper instance;

    private static final String DATABASE_NAME = "airports.sqlite";
    private static final int DATABASE_VERSION = 1;

    private String selectedCountryIso = "NL";

    private Cursor c;

    public String getSelectedCountryIso() {
        return selectedCountryIso;
    }

    public void setSelectedCountryIso(String selectedCountryIso) {
        this.selectedCountryIso = selectedCountryIso;
    }

    private AirportDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // private to prevent objectification of this class.
        // objectification is bad!

        c = getAirports();
    }

    public static AirportDatabaseHelper getInstance(Context context){
        if(null == instance)
        {
            instance = new AirportDatabaseHelper(context);
        }

        return instance;
    }
    // iso country =  \"NL\"
    public Cursor getAirports(){
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT icao, name, iso_country, municipality, elevation, latitude, longitude FROM airports WHERE iso_country = \"" + selectedCountryIso + "\" ORDER BY icao ASC";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        return c;
    }

    public Cursor getAirportByIcao(String icao)
    {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT icao, name, iso_country, municipality, elevation, latitude, longitude FROM airports WHERE icao =" + "\"" + icao + "\"";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        return c;
    }

    public Airport getItem(int position)
    {
        c.moveToFirst();
        c.move(position);

        Log.d("AirportAdapter", "getItem Position: " + position);

        return Airport.createAirport(c);
    }
}
