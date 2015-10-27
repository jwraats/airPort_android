package jwraats.jackevers.nl.airportandroid;

import android.database.Cursor;

/**
 * Created by jack on 20-10-2015.
 */
public class Airport {

    public String icao, name, isoCountry, municipality;
    public double elevation;
    public double latitude, longitude;

    public static Airport createAirport(Cursor c)
    {
        String icao = c.getString(c.getColumnIndex("icao"));
        String name = c.getString(c.getColumnIndex("name"));
        String isoCountry = c.getString(c.getColumnIndex("isoCountry"));
        String municipality = c.getString(c.getColumnIndex("municipality"));
        String elevation = c.getString(c.getColumnIndex("elevation"));
        String latitude = c.getString(c.getColumnIndex("latitude"));
        String longitude = c.getString(c.getColumnIndex("longitude"));

        Airport ap = new Airport();
        ap.icao = icao;
        ap.name = name;
        ap.isoCountry = isoCountry;
        ap.municipality = municipality;
        ap.elevation = Double.parseDouble(elevation);
        ap.latitude = Double.parseDouble(latitude);
        ap.longitude = Double.parseDouble(longitude);

        return ap;
    }
}
