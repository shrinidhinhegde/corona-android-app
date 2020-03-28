package com.example.corona;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent, pendingIntent1, pendingIntent2;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReciever.class);
        Intent alarmIntent1 = new Intent(MainActivity.this, warmwater.class);
        Intent alarmIntent2 = new Intent(MainActivity.this, fruits.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
        pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this,0,alarmIntent1,0);
        pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this,0,alarmIntent2,0);

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firststart",true);

        if(firstStart) {
            start();
        }
        else{
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            finish();
            startActivity(intent);
        }
    }

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, 0, 1000 * 60 * 120, pendingIntent);

        AlarmManager manager1 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager1.setInexactRepeating(AlarmManager.RTC_WAKEUP,0,1000*60*120,pendingIntent1);

        AlarmManager manager2 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager2.setInexactRepeating(AlarmManager.RTC_WAKEUP,0,1000*60*360,pendingIntent2);

        Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firststart",false);
        editor.apply();
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }

}
