package com.example.chiza12rpl012018;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.chiza12rpl012018.Fragment.CustomersFragment;
import com.example.chiza12rpl012018.Fragment.DashboardFragment;
import com.example.chiza12rpl012018.Fragment.HistoryFragment;
import com.example.chiza12rpl012018.Fragment.NotificationFragment;
import com.example.chiza12rpl012018.Fragment.ProfileFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

//import com.example.chiza12rpl012018.Adapter.Adapter;
//import com.example.chiza12rpl012018.Model.Model;

public class MainActivity extends AppCompatActivity {

    //    DrawerLayout drawerLayout;
//    NavigationView navigationView;
//    Toolbar toolbar;
//    RecyclerView recyclerView;
//    Adapter adapter;
    private static final String TAG = SplashscreenActivity.class.getSimpleName();
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
//        toolbar = findViewById(R.id.toolbar);
//        recyclerView = findViewById(R.id.recyclerview);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new Adapter(this, getList());
//        recyclerView.setAdapter(adapter);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
//        toolbar.setTitle("");
//        toolbar.setSubtitle("");
        //toolbar.setLogo(R.drawable.ic_toolbar);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        bottomNav = findViewById(R.id.nav_bottom);

        sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        final String role = sharedPreferences.getString("role", "");

        if (role.equalsIgnoreCase("Customer")) {
            bottomNav.setItemEnabled(R.id.customers, false);
        }

        if (savedInstanceState == null) {
            bottomNav.setItemSelected(R.id.home, true);
            fragmentManager = getSupportFragmentManager();
            DashboardFragment dashboardFragment = new DashboardFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, dashboardFragment)
                    .commit();
        }
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.dashboard:
                        fragment = new DashboardFragment();
                        break;
                    case R.id.history:
                        fragment = new HistoryFragment();
                        break;
                    case R.id.notification:
                        fragment = new NotificationFragment();
                        break;
                    case R.id.customers:
                        fragment = new CustomersFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                }
                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }else {
                    Log.e (TAG, "Error in creating fragment");
                }
            }
        });
    }

//    private ArrayList<Model> getList() {
//        ArrayList<Model> models = new ArrayList<>();
//
//        Model m = new Model();
//        m.setTxtname("Sepeda");
//        m.setTxtdesc("descriptions");
//        m.setImg(R.drawable.killua);
//        models.add(m);
//
//        m = new Model();
//        m.setTxtname("Sepeda");
//        m.setTxtdesc("descriptions");
//        m.setImg(R.drawable.killua);
//        models.add(m);
//
//        m = new Model();
//        m.setTxtname("Sepeda");
//        m.setTxtdesc("descriptions");
//        m.setImg(R.drawable.killua);
//        models.add(m);
//
//        m = new Model();
//        m.setTxtname("Sepeda");
//        m.setTxtdesc("descriptions");
//        m.setImg(R.drawable.killua);
//        models.add(m);
//
//        m = new Model();
//        m.setTxtname("Sepeda");
//        m.setTxtdesc("descriptions");
//        m.setImg(R.drawable.killua);
//        models.add(m);
//
//        return models;
//    }
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }else {
//            super.onBackPressed();
//        }
//    }
}