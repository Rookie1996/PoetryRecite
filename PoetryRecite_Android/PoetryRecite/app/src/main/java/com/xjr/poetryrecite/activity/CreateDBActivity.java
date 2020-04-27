package com.xjr.poetryrecite.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xjr.poetryrecite.R;

import org.litepal.LitePal;


public class CreateDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createdb_layout);
        Button createDB = (Button)findViewById(R.id.btn_createdb);
        createDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
                Intent intent = new Intent(CreateDBActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
