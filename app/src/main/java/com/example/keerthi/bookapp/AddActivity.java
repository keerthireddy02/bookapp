package com.example.keerthi.bookapp;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity implements View.OnClickListener {
    private Button addTodoBtn;
    private EditText titleEditText;
    private EditText authorEditText;
    private EditText dateEditText;

    private DBManager dbManager;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        setTitle("Add Note");
        titleEditText = findViewById(R.id.bookname);
        authorEditText = (EditText) findViewById(R.id.author);
        dateEditText=findViewById(R.id.datecreated);

        addTodoBtn = (Button) findViewById(R.id.add_book);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_book:

                final String name = titleEditText.getText().toString();
                final String desc = authorEditText.getText().toString();
                final String date=dateEditText.getText().toString();

                dbManager.insert(name, desc, date);

                Intent main = new Intent(AddActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;

        }
    }
}
