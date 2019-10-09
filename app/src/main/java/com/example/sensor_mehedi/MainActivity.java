package com.example.sensor_mehedi;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerid);
        NavigationView navigationView =(NavigationView) findViewById(R.id.navid);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.homeid){
            Intent intent = new Intent(this,home.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.accelerometer){
            Intent intent = new Intent(this,accelerometer.class);
            startActivity(intent);
        }

        else if(item.getItemId()==R.id.gyroscope){
            Intent intent = new Intent(this,gyroscope.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.proximity){
            Intent intent = new Intent(this,proximity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.shake){
            Intent intent = new Intent(this,shake.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.map){
            Intent intent = new Intent(this,map.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.data){
            Intent intent = new Intent(this,data.class);
            startActivity(intent);
        }
        return false;
    }
}
