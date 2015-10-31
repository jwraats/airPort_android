package jwraats.jackevers.nl.airportandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class CountrySelectionActivity extends AppCompatActivity implements countryFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selection);


    }

    @Override
    public void onFragmentInteraction(String id) {
        Log.d("CountrySelectionActivit", id);
        //TODO check if string is a valid country iso with to be made function in the dbhelper isValidCountryIso(String)
        AirportDatabaseHelper.getInstance(this).setSelectedCountryIso(id);
        //finish this activity. thus go back
        this.finish();
    }
}
