package com.asterixsolution.ruia.vacanza;

import android.content.Intent;
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

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawer;
    FragmentTransaction fT;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
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
        tvUserName.setText("Welcome " + getIntent().getStringExtra("NAME"));
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
