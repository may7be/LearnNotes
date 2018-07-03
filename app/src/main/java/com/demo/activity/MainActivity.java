package com.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.myapplication.R;
import com.demo.rxjava2.Demo;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Demo.testFlatMap();
        TextView tvAct = findViewById(R.id.tv_act);
        tvAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_SHORT).show();
                Log.i("aa", "click");
                Demo.testConcatMap();
            }
        });
    }
}
