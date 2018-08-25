package com.example.jalil.bimarstan;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Detail_darogeramActivity extends FragmentActivity implements OnMapReadyCallback{


    public String id;
    public String text;
    public String address;
    public String lat;
    public String lng;
    public String mobile;
    public String mobile2;
    private GoogleMap googleMap;
    private LatLng myLocation_latlng;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_darogeram);


        G.activity=this;
        Bundle bundle = getIntent().getExtras();
        id=bundle.getString("id");
        text=bundle.getString("text");
        address=bundle.getString("address");
        lat=bundle.getString("lat");
        lng=bundle.getString("lng");
        mobile=bundle.getString("mobile");
        mobile2=bundle.getString("mobile2");



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        TextView nameT=(TextView) findViewById(R.id.name);
        nameT.setText(text);

        TextView addressT=(TextView) findViewById(R.id.address);
        addressT.setText(address);


         TextView mobile1T=(TextView) findViewById(R.id.mobile1);
        mobile1T.setText(mobile);

        TextView mobile2T=(TextView) findViewById(R.id.mobile2);
        mobile2T.setText(mobile2);




        mobile1T.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                TextView mobile11T=(TextView) findViewById(R.id.mobile1);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(mobile11T.getText().toString())));
                startActivity(intent);

            }
        });






        mobile2T.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                TextView mobile2T=(TextView) findViewById(R.id.mobile2);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(mobile2T.getText().toString())));
                startActivity(intent);

            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap=googleMap;

        myLocation_latlng=new LatLng(Double.parseDouble(lat),Double.parseDouble(lng));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation_latlng, 14));

        googleMap.addMarker(new MarkerOptions().position(myLocation_latlng));
    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
