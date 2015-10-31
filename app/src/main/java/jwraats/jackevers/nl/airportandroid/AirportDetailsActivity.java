package jwraats.jackevers.nl.airportandroid;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class AirportDetailsActivity extends FragmentActivity implements OnMapReadyCallback, AirportDetailsPaneFragment.OnFragmentInteractionListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    Location lastLocation;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_details);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);

        lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng destination;
        Bundle extras = getIntent().getExtras();
        if(null != extras)
        {
            Airport ap = extras.getParcelable("Airport");
            destination = new LatLng(ap.latitude, ap.longitude);

            // Add a marker and move the camera
            Marker destinationMarker = mMap.addMarker(new MarkerOptions().position(destination).title(ap.name));
            destinationMarker.showInfoWindow();

            Marker currentLocationMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude())));
            currentLocationMarker.setIcon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation));

            mMap.addPolyline(new PolylineOptions().add(currentLocationMarker.getPosition(), destinationMarker.getPosition()).geodesic(true));

            //build bounds that fit the two markers for the camera to look at
            LatLngBounds.Builder builder =  new LatLngBounds.Builder();
            builder.include(currentLocationMarker.getPosition());
            builder.include(destinationMarker.getPosition());

            LatLngBounds cameraBounds = builder.build();

            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraBounds, 800, 800, 200));
        }
    }
}
