package com.example.android_dawd.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_dawd.R;
import com.example.android_dawd.database.DBHelper;

public class UserList extends AppCompatActivity {
    private DBHelper db;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    public UserList() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        db = new DBHelper(this);
        ListView lvProduct = (ListView) findViewById(R.id.lvProduct);

        cursor = db.getAllProducts();

        adapter = new SimpleCursorAdapter(this, R.layout.item_product, cursor, new String[]{
                DBHelper.ID, DBHelper.NAME, DBHelper.QUANTITY
        }, new int[]{
                R.id.tvID, R.id.tvName, R.id.tvQuantity
        }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lvProduct.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cursor = db.getAllProducts();
        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
        db.close();
    }
}
