package com.demo.activity;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.demo.DispatchAdapter;
import com.demo.bean.DispatchBean;
import com.demo.myapplication.R;
import com.demo.myapplication.databinding.ActivityMainBinding;
import com.demo.rxjava2.Demo;
import com.zg.android_utils.util_common.RomUtils;

import java.util.ArrayList;
import java.util.List;

import demo.com.debugutil.MainMenuManager;


public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mBinding;
    private DispatchAdapter mDispatchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        testRxJava();
        testDebugMenu();
        testDispatchEvent();
    }

    private void testRxJava() {
        Demo.testFlatMap();
        mBinding.btnRj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_SHORT).show();
                Log.i("aa", "click");
                Demo.testConcatMap();
            }
        });
        mBinding.btnDispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("rom_util", "is nubia: " + RomUtils.isNubia());

                //fake
                List<DispatchBean> dataList = new ArrayList<>();

                dataList.add(new DispatchBean("isNubia:" + RomUtils.isNubia()));
                dataList.add(new DispatchBean("brand:" + RomUtils.getBrand()));
                dataList.add(new DispatchBean("manufacturer:" + RomUtils.getManufacturer()));
                dataList.add(new DispatchBean("romName:" + RomUtils.getRomInfo().getName()));
                mDispatchAdapter.setNewData(dataList);
            }
        });
    }

    private void testDebugMenu() {
        MainMenuManager.setupMainMenu(mBinding.btnDebugMenu);
    }

    private void testDispatchEvent() {
        initView();
        initData();
    }

    private void initView() {
        mBinding.tv1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "onLongClick: tv1");
                mBinding.et1.setVisibility(View.VISIBLE);
                mBinding.tv1.setVisibility(View.GONE);
                return true;
            }
        });
        mBinding.et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i(TAG, "onFocusChange: et1" + hasFocus);
                if (hasFocus) {

                } else {
                    mBinding.et1.setVisibility(View.GONE);
                    mBinding.tv1.setVisibility(View.VISIBLE);
                }
            }
        });

        //recycler
        mBinding.rvDispatch.setLayoutManager(new LinearLayoutManager(this));
        mDispatchAdapter = new DispatchAdapter(R.layout.item_main, new ArrayList<DispatchBean>());
        mBinding.rvDispatch.setAdapter(mDispatchAdapter);
    }


    private void initData() {
        //fake
        List<DispatchBean> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add(new DispatchBean("dis" + i));
        }
        mDispatchAdapter.setNewData(dataList);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
