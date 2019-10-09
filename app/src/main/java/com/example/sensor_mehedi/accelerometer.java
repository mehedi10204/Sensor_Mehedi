package com.example.sensor_mehedi;


import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class accelerometer extends AppCompatActivity implements SensorEventListener {

    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;
    DatabaseReference reff;
    member member ;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        SM=(SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor=SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        xText=(TextView)findViewById(R.id.xtext);
        yText=(TextView)findViewById(R.id.ytext);
        zText=(TextView)findViewById(R.id.ztext);
        member = new member();

        reff= FirebaseDatabase.getInstance().getReference().child("member");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        int x = (int)(sensorEvent.values[0]);
        int y = (int)(sensorEvent.values[1]);
        int z = (int)(sensorEvent.values[2]);

        xText.setText("X: " + x);
        yText.setText("Y: " + y);
        zText.setText("Z: " + z);

        member.setXtext(x);
        member.setXtext(y);
        member.setXtext(z);
        reff.push().setValue(member);
        reff.child(String.valueOf(maxid+1)).child("member");




        if (x >= 9) {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            Toast toast = Toast.makeText(getApplicationContext(), "LEFT", Toast.LENGTH_SHORT); toast.show();

            Toast toast1 = Toast.makeText(getApplicationContext(), "Updating database", Toast.LENGTH_SHORT); toast1.show();


        }
        else if(x<=-9)
        {
            getWindow().getDecorView().setBackgroundColor(Color.CYAN);
            Toast toast = Toast.makeText(getApplicationContext(), "RIGHT", Toast.LENGTH_SHORT); toast.show();

            Toast toast1 = Toast.makeText(getApplicationContext(), "Updating database", Toast.LENGTH_SHORT); toast1.show();


        }
        else if(y>=9)
        {
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            Toast toast = Toast.makeText(getApplicationContext(), "STRAIGHT", Toast.LENGTH_SHORT); toast.show();

            Toast toast1 = Toast.makeText(getApplicationContext(), "Updating database", Toast.LENGTH_SHORT); toast1.show();


        }
        else
        {
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);    }

    @Override
    protected void onPause() {

        super.onPause();
        SM.unregisterListener(this);
    }
}
