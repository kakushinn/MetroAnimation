package com.example.administrator.metroanimationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.c_joke).setOnClickListener(this);
        findViewById(R.id.c_constellation).setOnClickListener(this);
        findViewById(R.id.c_idea).setOnClickListener(this);
        findViewById(R.id.c_recommend).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.c_joke:
                Toast.makeText(MainActivity.this, "joke", Toast.LENGTH_SHORT).show();
                break;
            case R.id.c_constellation:
                Toast.makeText(MainActivity.this, "constellation", Toast.LENGTH_SHORT).show();
                break;
            case R.id.c_idea:
                Toast.makeText(MainActivity.this, "creative", Toast.LENGTH_SHORT).show();
                break;
            case R.id.c_recommend:
                Toast.makeText(MainActivity.this, "recommend", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
