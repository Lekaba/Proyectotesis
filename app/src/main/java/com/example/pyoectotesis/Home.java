package com.example.pyoectotesis;

import android.os.Bundle;

import com.example.pyoectotesis.Common.Common;
import com.example.pyoectotesis.Mode.Statusmedidor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    FirebaseDatabase database;
    DatabaseReference statusmedidor;
    DatabaseReference statusmedidor_agua;
    DatabaseReference statusmedidor_gas;
    DatabaseReference statusmedidor_kwh;
    DatabaseReference statusmedidor_t;

    TextView txtFullName;
    TextView Value_agua,Value_gas,Value_kwh,Value_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Energia");
        setSupportActionBar(toolbar);


        Value_t = (TextView)findViewById(R.id.Value_t);
        Value_agua = (TextView)findViewById(R.id.Value_agua);
        Value_gas = (TextView)findViewById(R.id.Value_gas);
        Value_kwh = (TextView)findViewById(R.id.Value_kwh);


        database = FirebaseDatabase.getInstance();
        statusmedidor = FirebaseDatabase.getInstance().getReference().child("statusmedidor").child("1");
        statusmedidor_agua = FirebaseDatabase.getInstance().getReference().child("statusmedidor").child("medidor_agua");
        statusmedidor_gas = FirebaseDatabase.getInstance().getReference().child("statusmedidor").child("medidor_gas");
        statusmedidor_kwh = FirebaseDatabase.getInstance().getReference().child("statusmedidor").child("medidor_kwh");
        statusmedidor_t = FirebaseDatabase.getInstance().getReference().child("statusmedidor").child("medidor_t");



        statusmedidor_t.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("Value").getValue().toString();
                Value_t.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        statusmedidor_agua.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("Value").getValue().toString();
                Value_agua.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        statusmedidor_gas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("Value").getValue().toString();
                Value_gas.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        statusmedidor_kwh.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("Value").getValue().toString();
                Value_kwh.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "correo enviado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView)headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currentuser.getName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
