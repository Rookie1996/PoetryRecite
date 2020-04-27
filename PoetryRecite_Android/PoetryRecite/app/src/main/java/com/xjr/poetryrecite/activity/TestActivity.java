package com.xjr.poetryrecite.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xjr.poetryrecite.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        TextView title = (TextView)findViewById(R.id.test_title);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "font/poetry_default.otf"));
        Button startTest = findViewById(R.id.btn_mnks);
        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this , TestAnswerActivity.class);
                startActivity(intent);
            }
        });
    }
}
