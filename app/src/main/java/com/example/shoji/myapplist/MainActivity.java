package com.example.shoji.myapplist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ①データの準備
        List<String> items = new ArrayList<>();
        items.add("おみくじ");
        items.add("姓名判断");
        items.add("ストップウォッチ");
        items.add("ブラウザ");
        items.add("クイズ");
        items.add("メモ");
        items.add("ファイルデータ");
        items.add("データベース");

        // ②Adapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

        // ③Adapterを適用
        final ListView myListView = (ListView)findViewById(R.id.myListView);
        myListView.setAdapter(arrayAdapter);

        // ④クリック時のイベントハンドラを登録
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String s = myListView.getItemAtPosition(position).toString();

                if (s.equals("おみくじ"))
                {
                    Intent intent = new Intent(MainActivity.this, NameScore.class);
                    startActivity(intent);
                }
                else if(s.equals("ファイルデータ"))
                {
                    Intent intent = new Intent(MainActivity.this, FileData.class);
                    startActivity(intent);
                }
                else if(s.equals("データベース"))
                {
                    Intent intent = new Intent(MainActivity.this, Database.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
