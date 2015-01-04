package com.example.shoji.myapplist;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class NameScore extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_score);
    }

    public void getNameScore(View view)
    {
        TextView tv = (TextView)findViewById(R.id.myText);
        Random randomGenerator = new Random();
        String[] results = { "大吉", "小吉", "凶"};
        int num = randomGenerator.nextInt(results.length);   // 0～2

        // スタイルを変更する
        switch (num)
        {
            case 0:
                tv.setTextColor(Color.parseColor("red"));
                break;
            case 1:
            case 2:
                tv.setTextColor(Color.BLACK);
                break;
            default:
                break;
        }

        tv.setText(results[num]);
    }
}
