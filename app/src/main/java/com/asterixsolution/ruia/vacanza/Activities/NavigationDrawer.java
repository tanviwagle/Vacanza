package com.asterixsolution.ruia.vacanza.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.asterixsolution.ruia.vacanza.Fragments.Feedback;
import com.asterixsolution.ruia.vacanza.Fragments.HomePage;
import com.asterixsolution.ruia.vacanza.Fragments.PlaceInfo;
import com.asterixsolution.ruia.vacanza.PreferenceManager;
import com.asterixsolution.ruia.vacanza.R;
import com.asterixsolution.ruia.vacanza.Fragments.SearchHotels;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawer;
    FragmentTransaction fT;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fT=getSupportFragmentManager().beginTransaction();
        fT.replace(R.id.container,new HomePage());
        fT.commit();
        getSupportActionBar().setTitle("Home");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeader = navigationView.getHeaderView(0);
        TextView tvUserName = navHeader.findViewById(R.id.tvUsername);
        final PreferenceManager pref = new PreferenceManager(this);
        tvUserName.setText(pref.getName());
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(item.getItemId())
        {
            case R.id.Home:
            {
                fT=getSupportFragmentManager().beginTransaction();
                fT.replace(R.id.container,new HomePage());
                fT.commit();
                getSupportActionBar().setTitle("Home");
                item.setChecked(true);
                drawer.closeDrawers();
                break;
            }
            case R.id.Place:
            {
                fT=getSupportFragmentManager().beginTransaction();
                fT.replace(R.id.container,new PlaceInfo());
                fT.commit();
                getSupportActionBar().setTitle("Search Place");
                item.setChecked(true);
                drawer.closeDrawers();
                break;
            }
            case R.id.Hotel:
            {
                fT=getSupportFragmentManager().beginTransaction();
                fT.replace(R.id.container,new SearchHotels());
                fT.commit();
                getSupportActionBar().setTitle("Search Hotel");
                item.setChecked(true);
                drawer.closeDrawers();
                break;
            }
            case R.id.Profile:
            {
                startActivity(new Intent(this,Profile.class));
                break;
            }
            case R.id.share:
            {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String msg = "Let me recommend you this application\nVACANZA";
                i.putExtra(Intent.EXTRA_TEXT, msg);
                startActivity(Intent.createChooser(i, "Choose application to share"));
                break;
            }
            case R.id.feedback:
            {
                fT=getSupportFragmentManager().beginTransaction();
                fT.replace(R.id.container,new Feedback());
                fT.commit();
                getSupportActionBar().setTitle("Feedback");
                item.setChecked(true);
                drawer.closeDrawers();
                break;
            }
        }
        return false;
    }


}
