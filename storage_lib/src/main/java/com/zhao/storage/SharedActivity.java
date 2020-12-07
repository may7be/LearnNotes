package com.zhao.storage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.zhao.storage.databinding.ActivitySharedBinding;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @Author zhao on 2020/12/7
 * desc: android 11上的共享存储demo
 * 需要通过MediaStore API或者Storage Access Framework方式访问
 * 一般可通过Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)来获取目录：
 * /storage/emulated/0/MUSIC.
 * DCIM、Pictures、Alarms, Music, Notifications,Podcasts, Ringtones、Movies、Download等目录。
 * TODO:
 * 1. 相关详细目录
 * 2. 在不同版本(区分10以下10、11即可)读写时需要的权限
 * 3. MediaStore api
 * 4. Storage Access Framework
 */
public class SharedActivity extends AppCompatActivity {

    private ActivitySharedBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shared);
        init();
    }

    private void init() {
        binding.tvReadDirectory.setOnClickListener(v -> {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            binding.tvDirectoryContent.setText(externalStoragePublicDirectory.getAbsolutePath());
        });

    }

    public static void show(Context context) {
        Intent intent = new Intent(context, SharedActivity.class);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
