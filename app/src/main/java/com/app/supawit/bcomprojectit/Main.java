package com.app.supawit.bcomprojectit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button a ;
    TextView txtv1;
    ConnectionSQL connectionSQL;
    String st = null;
    String area = null;
    Statement stmt = null;
    ResultSet rs = null;
    NavigationView navigationView = null;
    Toolbar toolbar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        //Set Fragment
       /* HOME fragment = new HOME();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_con,fragment);
        fragmentTransaction.commit();*/
        if(savedInstanceState == null)
        {
            area = getIntent().getExtras().getString("STCODE");
            HOME fragment = new HOME();

            Bundle bundle = new Bundle();
            bundle.putString("Key",area);
            fragment.setArguments(bundle);


            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_con,fragment);
            fragmentTransaction.commit();
        }



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        st = getIntent().getExtras().getString("STCODE");
        txtv1 = (TextView) findViewById(R.id.txtname);

        try {
            connectionSQL = new ConnectionSQL();
            Connection con = connectionSQL.CONN();
            stmt = con.createStatement();
            String query = "USE[CMD-BX]" +
                           "select * from mas_st " +
                           "where stcode = '" + st +"'" ;
            rs = stmt.executeQuery(query);

            if(rs.next())
            {
                String fn = rs.getString("FULLNAME");
                txtv1.setText(fn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            /*HOME fragment = new HOME();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_con,fragment).commit();*/

            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.executePendingTransactions();
            fragmentManager.popBackStack("tag",1);
            fragmentManager.executePendingTransactions();


        }
        else if (id == R.id.nav_manage) {


           /* EditFragment fragment = new EditFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_con,fragment);
            fragmentTransaction.com mit();*/
            /*Intent edit = new Intent(getApplicationContext(),Edit.class);
            edit.putExtra("STCODE",st);
            startActivity(edit);*/

        }
        else if (id == R.id.nav_exit) {
            Intent login = new Intent(getApplicationContext(),Login.class);
            startActivity(login);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}