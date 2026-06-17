package com.example.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    TextView textView;
    MapView mapView;
    Marker marcadorPosicaoAtual;

    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        mapView=findViewById(R.id.mapview);
        Configuration.getInstance().setUserAgentValue(getPackageName());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mapinit();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //perdir permissão
        if (checkAndRequestPermission()){
            // Provedor de localização
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, location->{
                //aqui vc faz o tratamento com o evento location
                textView.setText("Latitude: " + location.getLatitude() +
                                "\nLongitude: " + location.getLongitude()+
                                 "\nAltitude: " + location.getAltitude() +
                                "\nVelocidade: " + location.getSpeed());
                showLocationOnMap(location);
            });

        }
    }
    public void mapinit(){
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(16.0);
    }
    public void showLocationOnMap(Location location){
        double latitude=location.getLatitude();
        double longitude=location.getLongitude();
        GeoPoint mLocation = new GeoPoint(latitude, longitude);
        mapView.getController().setCenter(mLocation);
        //Adiciona um overlay para mostrar a localização atual, (Marker)

        if (marcadorPosicaoAtual==null ){
             marcadorPosicaoAtual = new Marker(mapView);
        }

        marcadorPosicaoAtual.setPosition(mLocation);
        marcadorPosicaoAtual.setTitle("Minha Localização");
        //clear overlays para remover o marcador anterior
        //mapView.getOverlays().clear();
        mapView.getOverlays().add(marcadorPosicaoAtual);


    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida
            } else {
                checkAndRequestPermission();
            }
        }
    }

    public boolean checkAndRequestPermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return false;
        }
        return true;
    }


}