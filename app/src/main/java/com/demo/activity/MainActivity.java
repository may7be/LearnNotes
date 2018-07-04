package com.demo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.demo.myapplication.R;
import com.demo.myapplication.databinding.ActivityMainBinding;
import com.demo.rxjava2.Demo;

import demo.com.debugutil.MainMenuManager;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Demo.testFlatMap();
        mBinding.tvAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_SHORT).show();
                Log.i("aa", "click");
                Demo.testConcatMap();
            }
        });

        MainMenuManager.setupMainMenu(mBinding.btnPopup);
    }
}
