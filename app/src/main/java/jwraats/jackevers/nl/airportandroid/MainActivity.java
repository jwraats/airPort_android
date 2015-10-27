package jwraats.jackevers.nl.airportandroid;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements AirportCollectionFragment.OnFragmentInteractionListener {

    Button countryButton;

    @Override
    public void onFragmentInteraction(Airport ap) {
        Log.i("MainActivity", "onFragmentInteraction: " + ap.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflate the country button on the top right and set its text.
        countryButton = (Button) findViewById(R.id.countryButton);
        countryButton.setText(getSelectedCountryIso());

        countryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "clicked on country button.");
                startActivity(new Intent(MainActivity.this, CountrySelectionActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);

        countryButton.setText(getSelectedCountryIso());
    }

    private String getSelectedCountryIso()
    {
        return AirportDatabaseHelper.getInstance(this).getSelectedCountryIso();
    }
}
