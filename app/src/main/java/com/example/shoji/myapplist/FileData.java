package com.example.shoji.myapplist;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class FileData extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_data);
    }

    // [保存]ボタンクリック時に呼び出される
    public void onClick(View view)
    {
        try
        {
            // memo.datへの書き込みを準備
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            openFileOutput("memo.txt", Context.MODE_WORLD_READABLE)));

            // EditTextへの入力値をファイルに書き込み
            EditText editText = (EditText) findViewById(R.id.txtMemo);
            writer.write(editText.getText().toString());

            // ファイルをクローズする
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // [保存]ボタンクリック時に呼び出される
    public void GetFileData(View view)
    {
        // 読み込んだデータを取得するための器を準備
        StringBuffer str = new StringBuffer();

        try
        {
            // memo.datへの書き込みを準備
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            openFileInput("memo.txt")));

            //
            while (reader.ready())
            {
                str.append(reader.readLine());
            }

            // ファイルをクローズする
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        TextView textView = (TextView) findViewById(R.id.textFromFile);
        textView.setText(str.toString());
    }
}
