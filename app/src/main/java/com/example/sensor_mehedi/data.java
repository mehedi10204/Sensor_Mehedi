package com.example.sensor_mehedi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class data  extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener*/ {

    DatabaseReference reff;
    GridView grid;
    ArrayAdapter<String> adapter;
    List<String> slist;
    member member;
/*
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;


 */
    TextView gt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
/*
        drawerLayout = (DrawerLayout) findViewById(R.id.ret_gyro_drawerid);
        navigationView =(NavigationView) findViewById(R.id.ret_gyro_nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        toggle = new ActionBarDrawerToggle(data.this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


 */

        gt1= findViewById(R.id.ret_t1);
//        Calendar c=Calendar.getInstance();
//        final String dd= DateFormat.getDateTimeInstance().format(c.getTime());
//        gt1.setText(dd);
        slist = new ArrayList<>();

        member=new member();
        grid = findViewById(R.id.grid);
        //Initializing the Headings of the columns
        slist.add("X-axis");
        slist.add("Y-axis");
        slist.add("Z-axis");

        //The gridView is using the sample_layout.xml for Build up
        adapter = new ArrayAdapter<String>(this, R.layout.sample_layout, R.id.t1, slist);

        //Getting the value from database
        reff= FirebaseDatabase.getInstance().getReference("member");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    member res =dataSnapshot1.getValue(member.class);
                    //The values are added to the list
                    slist.add(Float.toString(res.xtext));
                    slist.add(Float.toString(res.ytext));
                    slist.add(Float.toString(res.ztext));
                }
                //the values are added to the adapter and subsequently to the List
                grid.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }



    private void goToActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        drawerLayout.closeDrawer(GravityCompat.START);
        startActivity(intent);
    }
/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

 */
}
