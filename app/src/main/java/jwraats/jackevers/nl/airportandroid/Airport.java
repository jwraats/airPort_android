package jwraats.jackevers.nl.airportandroid;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jack on 20-10-2015.
 */
public class Airport implements Parcelable {

    public String icao, name, isoCountry, municipality;
    public double elevation;
    public double latitude, longitude;

    protected Airport(Parcel in) {
        icao = in.readString();
        name = in.readString();
        isoCountry = in.readString();
        municipality = in.readString();
        elevation = in.readDouble();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Airport> CREATOR = new Creator<Airport>() {
        @Override
        public Airport createFromParcel(Parcel in) {
            return new Airport(in);
        }

        @Override
        public Airport[] newArray(int size) {
            return new Airport[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(icao);
        dest.writeString(name);
        dest.writeString(isoCountry);
        dest.writeString(municipality);
        dest.writeDouble(elevation);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    public Airport(){}

    @Override
    public String toString() {
        return "Airport{" +
                "icao='" + icao + '\'' +
                ", name='" + name + '\'' +
                ", isoCountry='" + isoCountry + '\'' +
                ", municipality='" + municipality + '\'' +
                ", elevation=" + elevation +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public static Airport createAirport(Cursor c)
    {
        String icao = c.getString(c.getColumnIndex("icao"));
        String name = c.getString(c.getColumnIndex("name"));
        String isoCountry = c.getString(c.getColumnIndex("iso_country"));
        String municipality = c.getString(c.getColumnIndex("municipality"));
        String elevation = c.getString(c.getColumnIndex("elevation"));
        String latitude = c.getString(c.getColumnIndex("latitude"));
        String longitude = c.getString(c.getColumnIndex("longitude"));

        Airport ap = new Airport();
        ap.icao = icao;
        ap.name = name;
        ap.isoCountry = isoCountry;
        ap.municipality = municipality;
        if(0 != elevation.length())
            ap.elevation = Double.parseDouble(elevation);
        else
            ap.elevation = 0.0;

        if(0 != latitude.length())
            ap.latitude = Double.parseDouble(latitude);
        else
            ap.latitude = 0.0;

        if(0 != longitude.length())
            ap.longitude = Double.parseDouble(longitude);
        else
            ap.longitude = 0.0;

        return ap;
    }
}
