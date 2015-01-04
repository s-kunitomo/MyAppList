package com.example.shoji.myapplist;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class Database extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor>
{
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        // ListView <> CursorLoader <> ContentsProvider <> DB
        // ListView -> adapterの設定 ->ListViewの取得 -> ListViewにadapterを設定
        // adapterの設定
        String[] from = { MyAppContract.Users.COLUMN_NAME, MyAppContract.Users.COLUMN_SCORE };
        int[] to = { android.R.id.text1, android.R.id.text2 };
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, from, to, 0);

        // ListViewの取得
        ListView listView = (ListView) findViewById(R.id.myListView);

        // ListViewにadapterを設定
        listView.setAdapter(adapter);

        // Click Event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // insert
//                ContentValues values = new ContentValues();
//                values.put(MyAppContract.Users.COLUMN_NAME, "tanaka");
//                values.put(MyAppContract.Users.COLUMN_SCORE, 50);
//                getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

                // delete
//                Uri uri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, id);
//                String selection = MyAppContract.Users.COLUMN_ID + " = ?";
//                String[] selectionArgs = new String[] { Long.toString(id) };
//                getContentResolver().delete(uri, selection, selectionArgs);

                // update
                ContentValues contentValues = new ContentValues();
                contentValues.put(MyAppContract.Users.COLUMN_SCORE, 100);
                Uri uri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, id);
                String selection = MyAppContract.Users.COLUMN_ID + " = ?";
                String[] selectionArgs = new String[] { Long.toString(id) };
                getContentResolver().update(uri, contentValues, selection, selectionArgs);
            }
        });

        // (1) CursorLoaderの初期化
        getLoaderManager().initLoader(0, null, this);

        // DBのOpen
//        MyDbHelper myDbHelper = new MyDbHelper(this);
//        SQLiteDatabase db = myDbHelper.getWritableDatabase();

        // 各種設定
        // select
//        Cursor cursor = null;
//        try
//        {
//            cursor = db.query(
//                    MyAppContract.Users.TABLE_NAME,
//                    new String[] { MyAppContract.Users.COLUMN_ID, MyAppContract.Users.COLUMN_NAME, MyAppContract.Users.COLUMN_SCORE },
//                    "score > ?", new String[] { "60" },
//                    null,
//                    null,
//                    null
//            );
//            Log.v("DB_TEST", "Count: " + cursor.getCount());
//
//            while(cursor.moveToNext())
//            {
//                String name = cursor.getString(cursor.getColumnIndex(MyAppContract.Users.COLUMN_NAME));
//                int score = cursor.getInt(cursor.getColumnIndex(MyAppContract.Users.COLUMN_SCORE));
//                Log.v("DB_TEST", "name: " + name + " score: " + score);
//            }
//        }
//        finally
//        {
//            if (cursor != null) cursor.close();
//        }

        // insert
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MyAppContract.Users.COLUMN_NAME, "tanaka");
//        contentValues.put(MyAppContract.Users.COLUMN_SCORE, 74);
//        long newId = db.insert(MyAppContract.Users.TABLE_NAME, null, contentValues);
//        Log.v("DB_TEST", "Inserted ID: " + newId);

        // update
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MyAppContract.Users.COLUMN_SCORE, 100);
//        int updateCount = db.update(
//                MyAppContract.Users.TABLE_NAME,
//                contentValues,
//                "score > ?",
//                new String[] { "80" }
//        );
//        Log.v("DB_TEST", "updateCount:" + updateCount);

        // delete
//        int deletedCount = db.delete(
//                MyAppContract.Users.TABLE_NAME,
//                "score < ?",
//                new String[] { "50" }
//        );
//        Log.v("DB_TEST", "Deleted count:" + deletedCount);

        // transaction
//        try
//        {
//            // トランザクション開始
//            db.beginTransaction();
//
//            db.execSQL("update users set score = score - 10 where _id = 1");
//            db.execSQL("update users set score = score + 10 where _id = 2");
//
//            // 完了
//            db.setTransactionSuccessful();
//        }
//        finally
//        {
//            //
//            db.endTransaction();
//        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        String[] projection =
        {
            MyAppContract.Users.COLUMN_ID,
            MyAppContract.Users.COLUMN_NAME,
            MyAppContract.Users.COLUMN_SCORE
        };
        // (2) 非同期処理を投げる
        return new CursorLoader(
                this,
                MyContentProvider.CONTENT_URI,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
    {
        //  (3) 結果をadapterに反映
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        adapter.swapCursor(null);
    }
}
