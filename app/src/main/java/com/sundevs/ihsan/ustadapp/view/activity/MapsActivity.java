package com.sundevs.ihsan.ustadapp.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sundevs.ihsan.ustadapp.R;
import com.sundevs.ihsan.ustadapp.util.param.Params;
import com.sundevs.ihsan.ustadapp.util.param.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener,
        GoogleMap.OnMarkerClickListener, RoutingListener {
    private GoogleMap mMap;
    private String[] nama, alamat, harga, bidang, id_ustad, no_hp, email;
    int numData;
    LatLng latLng[];
    Boolean markerD[];
    private Double[] lat, lng;
    Criteria criteria;
    Location location;
    LatLng myPosition;
    LocationManager locationManager;
    private List<Polyline> polylines = new ArrayList<>();
    String provider;
    Double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        MapsActivity.this.getRoutingPath(0, 0);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            myPosition = new LatLng(latitude, longitude);
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(provider, 1000, 0, this);

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        getLokasi();
    }
    private void getLokasi() {
        JsonArrayRequest request = new JsonArrayRequest(URL.URL_JARAK, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                numData = response.length();
                Log.d("DEBUG_", "Parse JSON");
                latLng = new LatLng[numData];
                markerD = new Boolean[numData];
                nama = new String[numData];
                alamat = new String[numData];
                harga = new String[numData];
                bidang = new String[numData];
                id_ustad = new String[numData];
                no_hp = new String[numData];
                email = new String[numData];
                lat = new Double[numData];
                lng = new Double[numData];

                for (int i = 0; i < numData; i++) {
                    try {
                        JSONObject data = response.getJSONObject(i);
                        id_ustad[i] = data.getString(Params.id_ustad);
                        latLng[i] = new LatLng(data.getDouble("lat"),
                                data.getDouble("lng"));
                        nama[i] = data.getString(Params.nama);
                        alamat[i] = data.getString(Params.alamat);
                        harga[i] = data.getString(Params.harga);
                        bidang[i] = data.getString(Params.bidang);
                        no_hp[i] = data.getString(Params.no_hp);
                        email[i] = data.getString(Params.email);
                        lat[i] = data.getDouble("lat");
                        lng[i] = data.getDouble("lng");
                        markerD[i] = false;
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng[i])
                                .title(nama[i])
                                .snippet(alamat[i])
                                .snippet(bidang[i])
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ustad)));
                    } catch (JSONException je) {
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng[i], 15.5f));
                }

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Log.d("DEBUG_", "Marker clicked");
                        for (int i = 0; i < numData; i++) {

                            if (marker.getTitle().equals(nama[i])) {
                                if (markerD[i]) {
                                    Log.d("DEBUG_", "panggil activity");
                                    getRoutingPath(lat[i], lng[i]);
                                    markerD[i] = false;
                                } else {
                                    Log.d("DEBUG_", "show info");
                                    // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15.5f));
                                    markerD[i] = true;
                                    marker.showInfoWindow();
                                    Toast ts = Toast.makeText(MapsActivity.this, "Klik marker kembali, untuk detail Ustad", Toast.LENGTH_LONG);
                                    TextView v = (TextView) ts.getView().findViewById(android.R.id.message);
                                    if (v != null)
                                        v.setGravity(Gravity.CENTER);
                                    ts.show();
                                }
                            } else {
                                markerD[i] = false;
                            }
                        }
                        return false;
                    }

                });
//                        if (PermissionHelper.canGetLocation()) {
//                            double range = Utility.distance(PermissionHelper.getLatitude(), gpsTracker.getLongitude(),
//                                    listGuru.get((listGuru.size() - 1)).getLatitude(),
//                                    listGuru.get((listGuru.size() - 1)).getLongitude());
//                            Log.d("gps_latitude", gpsTracker.getLatitude() + "");
//                            Log.d("gps_longitude", gpsTracker.getLongitude() + "");
//                            Toast.makeText(MapsActivity.this, range + "KM", Toast.LENGTH_LONG).show();
//                        }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                builder.setTitle("Error!");
                builder.setMessage("cek jaringan bro");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        Volley.newRequestQueue(this).add(request);
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        myPosition = new LatLng(latitude, longitude);
    }

    private void drawRoute(ArrayList<Route> routes) {
        if (polylines.size() > 0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        for (int i = 0; i < routes.size(); i++) {
            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(Color.BLUE);
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(routes.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        Toast.makeText(getApplicationContext(), "Routing Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {
        drawRoute(arrayList);

    }

    @Override
    public void onRoutingCancelled() {
        Toast.makeText(MapsActivity.this, "Routing Cancelled", Toast.LENGTH_SHORT).show();

    }

    private void getRoutingPath(double lat, double lng) {
        try {
            LatLng from = new LatLng(latitude, longitude);
            LatLng to = new LatLng(lat, lng);
            Routing routing = new Routing.Builder()
                    .travelMode(Routing.TravelMode.DRIVING)
                    .withListener(this)
                    .waypoints(from, to)
                    .build();
            routing.execute();
        } catch (Exception e) {

        }
    }

    @OnClick(R.id.ic_menu)
    void kembali (){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
