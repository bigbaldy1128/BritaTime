package com.example.wangjinzhao.britatime;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    TimeView timeView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeView1=(TimeView)this.findViewById(R.id.timeView1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        long leftDay=getLeftDay();
        timeView1.setLeftDay(leftDay);
        timeView1.invalidate();
    }

    public void resetTime(View view) {
        java.util.Date date=new java.util.Date();
        String data=sdf.format(date);
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("date_time", data);
        editor.commit();
        timeView1.setLeftDay(30);
        timeView1.invalidate();
    }

    public long getLeftDay()
    {
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        String data = pref.getString("date_time", "");
        if(data==null ||data.length()==0)
            return 0;
        Date start= null;
        try {
            start = sdf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now=new Date();
        long diff = now.getTime() - start.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return 30-days;
    }
}
