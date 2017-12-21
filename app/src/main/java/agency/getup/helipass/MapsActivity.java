package agency.getup.helipass;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.IOException;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        checkLocationPermission();
        deserializeJson();
    }

    public void deserializeJson() {
        boolean isWorking = true;
        ObjectMapper mapper = new ObjectMapper();
        try {
            //final File file = new File(Environment.getExternalStorageDirectory()
            //        .getAbsolutePath(), "helico.json");

            //File file = new File("helico.json");
            String dataJson = "[ { \\\"id\\\": \\\"7\\\", \\\"name\\\": \\\"Paris - Versailles\\\", \\\"latitude\\\" : 48.8335831, \\\"longitude\\\" : 2.2711787, \\\"images\\\" : [ \\\"/helipass-web/web/cache/media/product_details_thumb/uploads/media/default/0001/01/586bb618de6fa-paris-versailles-solo-jpg.jpeg\\\", \\\"/helipass-web/web/cache/media/product_details_thumb/uploads/media/default/0001/01/586bb618de6fa-paris-versailles-solo-jpg.jpeg\\\", \\\"/helipass-web/web/cache/media/product_details_thumb/uploads/media/default/0001/01/58454247cde92-helicoptere-paris-png\\\" , \\\"/helipass-web/web/cache/media/product_details_thumb/uploads/media/default/0001/01/58454247e47f0-vol-paris-versailles-helicoptere-jpg\\\" , \\\"/helipass-web/web/cache/media/product_details_thumb/uploads/media/default/0001/01/5845424800f33-paris-en-helico-tour-eiffel-jpg\\\" , \\\"/helipass-web/web/cache/media/product_details_thumb/uploads/media/default/0001/01/586bb4a6d2bd8-paris-vue-de-versailles-jpg.jpeg\\\" ], \\\"price\\\": {\\\"id\\\":14,\\\"price_h_t\\\":362.72727,\\\"price_t_t_c\\\":399,\\\"_t_v_a\\\":10,\\\"currency\\\":{\\\"id\\\":1,\\\"name\\\":\\\"euro\\\",\\\"symbol\\\":\\\"\\\\u20ac\\\",\\\"code\\\":\\\"EUR\\\",\\\"number\\\":\\\"978\\\",\\\"rates\\\":{\\\"USD\\\":\\\"1.08\\\",\\\"CAD\\\":\\\"1.5\\\"}}}, \\\"description\\\" : \\\"<p>Au d&eacute;part de <u><strong>l&#39;H&eacute;liport de Paris<\\\\/strong><\\\\/u>, embarquez &agrave; bord d&rsquo;un h&eacute;licopt&egrave;re bimoteur Airbus H135 de 6 places passagers, pour une d&eacute;couverte de l&rsquo;ouest parisien vu du ciel.<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>&nbsp;<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>1H30 d&rsquo;exp&eacute;rience magique dont&nbsp;25 min de vol inoubliable.<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>&nbsp;<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>Vous profitez du panorama : <strong>Rives ouest de la Seine<\\\\/strong>, <strong>Parc des Princes<\\\\/strong>, <strong>bois de Boulogne<\\\\/strong>, <strong>hippodrome de Longchamp<\\\\/strong>, <strong>parc de Saint-Cloud<\\\\/strong> ainsi que <strong>Versailles<\\\\/strong> et les <strong>jardins du Roi Soleil<\\\\/strong>.<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>Apr&egrave;s le survol de Paris et de ses monuments, partez &agrave; destination du chateau de Versailles.<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>&nbsp;<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>Vous vous poserez derri&egrave;re le Ch&acirc;teau , sur l&rsquo;a&eacute;rodrome de St Cyr l&rsquo;&eacute;cole.<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>Partagez un moment unique et chaleureux avec votre commandant de bord, c&rsquo;est l&rsquo;occasion d&rsquo;&eacute;changer sur l&rsquo;univers de l&rsquo;h&eacute;licopt&egrave;re et de prendre des photos.<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>&nbsp;<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>Du <strong>Trocad&eacute;ro<\\\\/strong> &agrave; la <strong>Tour Montparnasse<\\\\/strong>, vous terminerez par un aper&ccedil;u &laquo; plein champ &raquo; de la grande <strong>Tour Eiffel.<\\\\/strong><\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>&nbsp;<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>Pour d&eacute;couvrir Paris vu du ciel, pour f&ecirc;ter un &eacute;v&egrave;nement, n&#39;h&eacute;sitez pas un instant, moments inoubliables garantis !<\\\\/p>\\\", \\\"flightTime\\\" : \\\"25min\\\", \\\"totalTime\\\" : \\\"1h30\\\", \\\"address\\\" : \\\"61 rue Henry Farman\\\", \\\"city\\\" : \\\"Paris\\\", \\\"catchPhrase\\\" : \\\"Vivez une exp\\\\u00e9rience d'1h30 dont 25min de vol en h\\\\u00e9licopt\\\\u00e8re pour une d\\\\u00e9couverte de Paris et Versailles depuis le ciel.\\\" } ,\\t{ \\\"id\\\": \\\"8\\\", \\\"name\\\": \\\"Châteaux de la Loire\\\", \\\"latitude\\\" : 47.580477, \\\"longitude\\\" : 0.603486, \\\"images\\\" : [ \\\"/helipass-web/web/cache/media/product_details_thumb/uploads/media/default/0001/01/586bb947afff3-chateaux-loire-1-jpg.jpeg\\\" , \\\"/helipass-web/web/cache/media/product_details_thumb/uploads/media/default/0001/01/586bb94f3117d-chateaux-loire-3-jpg.jpeg\\\" , \\\"/helipass-web/web/cache/media/product_details_thumb/uploads/media/default/0001/01/586bb9509b108-chateaux-loire-2-jpg.jpeg\\\" ], \\\"price\\\": {\\\"id\\\":17,\\\"price_h_t\\\":209.09091,\\\"price_t_t_c\\\":230,\\\"_t_v_a\\\":10,\\\"currency\\\":{\\\"id\\\":1,\\\"name\\\":\\\"euro\\\",\\\"symbol\\\":\\\"\\\\u20ac\\\",\\\"code\\\":\\\"EUR\\\",\\\"number\\\":\\\"978\\\",\\\"rates\\\":{\\\"USD\\\":\\\"1.08\\\",\\\"CAD\\\":\\\"1.5\\\"}}}, \\\"description\\\" : \\\"<p>Envolez-vous &agrave; bord d&rsquo;un h&eacute;licopt&egrave;re pour voir autrement quelques ch&acirc;teaux prestigieux qui font la renomm&eacute;e de notre r&eacute;gion et autres somptueuses demeures :<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p style=\\\\\\\"margin-left:40px\\\\\\\"><strong>Ch&acirc;teau de la Haute Barde<\\\\/strong>, <strong>Grange de Meslay<\\\\/strong>, <strong>Ch&acirc;teau de Jallanges<\\\\/strong>, <strong>vignobles de Vouvray<\\\\/strong>, <strong>Ch&acirc;teau de Noizay<\\\\/strong>, <strong>Pagode de Chanteloup<\\\\/strong>, <strong>Clos Luc&eacute;<\\\\/strong>, <strong>Ch&acirc;teau d&#39;Amboise<\\\\/strong>, <strong>Jardins de Valmer<\\\\/strong>, <strong>Ch&acirc;teau de Beaumont-la-Ronce.<\\\\/strong>..<\\\\/p>\\\\r\\\\n\\\\r\\\\n<p>Votre balade en h&eacute;licopt&egrave;re sera charg&eacute;e d&#39;Histoire et vous offrira un souvenir magique des plus beaux lieux de France.<\\\\/p>\\\", \\\"flightTime\\\" : \\\"25min\\\", \\\"totalTime\\\" : \\\"25min\\\", \\\"address\\\" : \\\"Héliport de Belleville\\\", \\\"city\\\" : \\\"Neuvy-le-Roi\\\", \\\"catchPhrase\\\" : \\\"Circuit Loire et ch\\\\u00e2teaux\\\" }]";
            //Offer myObjects = mapper.readValue(dataJson, Offer.class);
            Offer[] myObjects = mapper.readValue(dataJson, Offer[].class);
            System.out.println("YAAAAAAAAAAAAAAAAAAAAAAA" + myObjects);
        } catch (IOException e) {
            e.printStackTrace();
            isWorking = false;
        }
    }

    // coucou


    public boolean checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }




    public void initializeLocationManager() {

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {

                LatLng position_user = new LatLng(location.getLatitude(), location.getLongitude());

                mMap.addMarker(new MarkerOptions().position(position_user).title("User Position"));

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }

        };
        // Register the listener with the Location Manager to receive location updates

        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        } catch (SecurityException e) {

            System.out.println("Pas cool, non, non");

        }


        String locationProvider = LocationManager.GPS_PROVIDER;


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        initializeLocationManager();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            initializeLocationManager();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.removeUpdates(locationListener);

        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng nowhere = new LatLng(50, 7);
        mMap.addMarker(new MarkerOptions().position(nowhere).title("nowhere"));
    }

}
