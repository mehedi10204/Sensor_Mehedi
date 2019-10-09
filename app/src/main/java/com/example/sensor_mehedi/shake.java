package com.example.sensor_mehedi;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.squareup.seismic.ShakeDetector;

public class shake extends AppCompatActivity  implements ShakeDetector.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        SensorManager SM=(SensorManager)getSystemService(SENSOR_SERVICE);
        ShakeDetector SD=new ShakeDetector(this);
        SD.start(SM);
    }

    @Override
    public void hearShake() {

        getWindow().getDecorView().setBackgroundColor(Color.RED);

        Toast toast = Toast.makeText(getApplicationContext(), "SHAKE DETECTED", Toast.LENGTH_SHORT); toast.show();



        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:01303262525"));
        startActivity(intent);



    }
}
