package com.example.sensor_mehedi;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class gyroscope extends AppCompatActivity implements SensorEventListener {

    private TextView xgyro,ygyro,zgyro;
    private Sensor mySensor;
    private SensorManager SM;
    DatabaseReference reff;
    member member ;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        SM=(SensorManager)getSystemService(SENSOR_SERVICE);

        mySensor=SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SM.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

        xgyro=(TextView)findViewById(R.id.xgyro);
        ygyro=(TextView)findViewById(R.id.ygyro);
        zgyro=(TextView)findViewById(R.id.zgyro);
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

        float x =sensorEvent.values[0];
        float y =sensorEvent.values[1];
        float z =sensorEvent.values[2];

        xgyro.setText("X: " + (int)x);
        ygyro.setText("Y: " + (int)y);
        zgyro.setText("Z: " + (int)z);

        member.setXtext((int) x);
        member.setXtext((int) y);
        member.setXtext((int) z);
        reff.push().setValue(member);
        reff.child(String.valueOf(maxid+1)).child("member");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onResume()
    {
        super.onResume();
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        Toast toast = Toast.makeText(getApplicationContext(), "INSERTING DATA INTO FIREBASE", Toast.LENGTH_LONG); toast.show();

    }
    protected void onPause()
    {
        super.onPause();
        SM.unregisterListener(this);
    }
}
