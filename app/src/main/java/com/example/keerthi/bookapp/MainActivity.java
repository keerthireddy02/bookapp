package com.example.keerthi.bookapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;
    private FloatingActionButton fab;
    private SimpleCursorAdapter adapter;
    private ImageButton del;
    private TextView id;
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.TITLE, DatabaseHelper.AUTHOR,DatabaseHelper.DATE };

    final int[] to = new int[] { R.id.id, R.id.title, R.id.author,R.id.datecre };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=findViewById(R.id.id);
        fab=findViewById(R.id.fab);
        dbManager = new DBManager(MainActivity.this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);

        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.list_cotent, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(MainActivity.this, AddActivity.class);
                startActivity(add_mem);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.delete(id.);
                this.returnHome();
            }
        });

        public void returnHome() {
            Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(home_intent);
        }

    }
}
