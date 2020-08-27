package com.example.chiza12rpl012018;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chiza12rpl012018.Adapter.Adapter;
import com.example.chiza12rpl012018.Model.Model;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, getList());
        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        //toolbar.setLogo(R.drawable.ic_toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private ArrayList<Model> getList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTxtname("Sepeda");
        m.setTxtdesc("descriptions");
        m.setImg(R.drawable.killua);
        models.add(m);

        m = new Model();
        m.setTxtname("Sepeda");
        m.setTxtdesc("descriptions");
        m.setImg(R.drawable.killua);
        models.add(m);

        m = new Model();
        m.setTxtname("Sepeda");
        m.setTxtdesc("descriptions");
        m.setImg(R.drawable.killua);
        models.add(m);

        m = new Model();
        m.setTxtname("Sepeda");
        m.setTxtdesc("descriptions");
        m.setImg(R.drawable.killua);
        models.add(m);

        m = new Model();
        m.setTxtname("Sepeda");
        m.setTxtdesc("descriptions");
        m.setImg(R.drawable.killua);
        models.add(m);

        return models;
    }
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}