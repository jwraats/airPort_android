package jwraats.jackevers.nl.airportandroid;

/**
 * Created by jack on 20-10-2015.
 */
public class AirportDatabaseHelper {

    private static AirportDatabaseHelper instance;

    private AirportDatabaseHelper(){
        // private to prevent objectification of this class.
        // objectification is bad!
    }

    public static AirportDatabaseHelper getInstance(){
        if(null == instance)
        {
            instance = new AirportDatabaseHelper();
        }

        return instance;
    }

    
}
