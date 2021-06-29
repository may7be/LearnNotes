package com.demo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.demo.myapplication.R;
import com.demo.myapplication.databinding.ActivityShare2Binding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @Author zhao on 6/29/21
 */
public class Share2Activity extends AppCompatActivity {
    private ActivityShare2Binding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_share2);
        init();
    }

    private void init() {
        String url = "https://i2.chuimg.com/b03533894f234056b7fa77536fdc900d_1080w_1080h.jpg?imageView2/1/w/160/h/160/q/90/format/webp";
        Glide.with(this).load(url).into(mBinding.iv2);
        mBinding.iv2.setOnClickListener(v -> onBackPressed());
    }

    public static void show(Context context) {
        Intent intent = new Intent(context, Share2Activity.class);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
