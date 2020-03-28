package com.example.corona;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView bn11 = findViewById(R.id.bn1);
        bn11.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                new FragmentGPA()).commit();

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch(item.getItemId()){
            case R.id.tool_who_web:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentWHOweb()).commit();
                return true;
            case R.id.tool_who_twitter:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentWHOtwitter()).commit();
                return true;
            case R.id.tool_moh_web:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentMOHweb()).commit();
                return true;
            case R.id.tool_moh_twitter:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentMOHtwitter()).commit();
                return true;
            case R.id.tool_stat_global:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentGLOB()).commit();//api
                return true;
            case R.id.tool_stat_state:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentSTATE()).commit();//api
                return true;
            case R.id.tool_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentCONTACT()).commit();//api
                return true;
            case R.id.tool_whatsapp:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=41798931892&text=hi&source=&data="));
                finish();
                startActivity(browserIntent);
                return true;
            case R.id.tool_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentABOUT()).commit();
                return true;
            case R.id.tool_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String share_body = "Check out this app which shows everything about Covid-19!\nDownload Here: https://drive.google.com/open?id=1hZJrJJxEvFpHEmQWW2uKylInfnJPLiWn \nDeveloped By: https://github.com/shrinidhinhegde \n\n#indiafightscoronavirus #covid19 #coronavirus #karnatakafightscoronavirus #corona #coronavirusinindia";
                share.putExtra(Intent.EXTRA_TEXT,share_body);
                startActivity(Intent.createChooser(share,"Share Using"));
                return true;
            case R.id.tool_report:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9154153916"));
                finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_gpa:
                            selectedFragment = new FragmentGPA();
                            break;
                        case R.id.nav_ipc:
                            selectedFragment = new FragmentIPC();
                            break;
                        case R.id.nav_gsw:
                            selectedFragment = new FragmentGSW();
                            break;
                        case R.id.nav_poe:
                            selectedFragment = new FragmentPOE();
                            break;
                        case R.id.nav_ani:
                            selectedFragment = new FragmentANI();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
