package com.aura.schedule;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Process;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        setContentView(ll);

        ScrollView sv = new ScrollView(this);
        ll.addView(sv);

        TextView tv = new TextView(this);
        // tv.setLines(2048);

        sv.addView(tv);

        Thread thread = new Thread() {
            @Override
            public void run() {

                Schedule schedule = null;
                try {
                    schedule = Parser.getSchedule();
                    if(schedule == null) throw new NullPointerException("error parsing");
                } catch (IOException e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "отсутствует\n интернет\n соединение", Toast.LENGTH_LONG);
                    toast.show();

                    int pid = Process.myPid();
                    Process.killProcess(pid);
                } catch (NullPointerException e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "ошибка при\n обработке данных", Toast.LENGTH_LONG);
                    toast.show();

                    int pid = Process.myPid();
                    Process.killProcess(pid);
                }

                StringBuilder sb = new StringBuilder();
                sb.append(schedule.getGroup("3РПУ-20-1"));
                sb.append("\n\n\n\n\n");

                for(String gr: schedule.getListGroups()) {
                    sb.append(gr);
                    sb.append("\n");
                }

                runOnUiThread(() -> tv.setText(sb.toString()));

            }
        };

        thread.start();

    }
}