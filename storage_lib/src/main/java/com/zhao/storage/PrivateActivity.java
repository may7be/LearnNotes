package com.zhao.storage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.zg.android_utils.util_common.FileUtil;
import com.zhao.storage.databinding.ActivityPrivateBinding;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @Author zhao on 2020/11/27
 * desc: android 11上的私有存储demo，不需要权限
 * 内部存储私有目录 (/data/data/packageName) ；
 * 外部存储私有目录 (/sdcard/Android/data/packageName)
 */
public class PrivateActivity extends AppCompatActivity {

    private ActivityPrivateBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_private);
        init();
    }

    private void init() {
        mBinding.tvIn.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder();
            File filesDir = getFilesDir();
            File cacheDir = getCacheDir();
            File dataDirectory = Environment.getDataDirectory();

            sb.append(filesDir.getAbsolutePath());
            sb.append("\n");
            sb.append(cacheDir.getAbsolutePath());
            sb.append("\n");
            sb.append(dataDirectory.getAbsolutePath());
            mBinding.tvInContent.setText(sb.toString());
        });
        mBinding.tvInWrite.setOnClickListener(v -> {
            String newPath = getFilesDir().getAbsolutePath() + "/test.txt";
            String s = "\n写入files/test.txt的第一行字符串";

            FileUtil.writeStringToFile(newPath, s);
            mBinding.tvInWriteContent.setText(newPath);
        });
        mBinding.tvInRead.setOnClickListener(v -> {
            String path = getFilesDir().getAbsolutePath() + "/test.txt";
            String s = FileUtil.readStringFromFilePath(path);
            mBinding.tvInReadContent.setText(s);
        });


        mBinding.tvOut.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder();
            File externalFilesDir = getExternalFilesDir(null);
            File externalCacheDir = getExternalCacheDir();

            if (externalFilesDir != null) {
                sb.append(externalFilesDir.getAbsolutePath());
                sb.append("\n");
            }
            if (externalCacheDir != null) {
                sb.append(externalCacheDir.getAbsolutePath());
                sb.append("\n");
            }
            mBinding.tvOutContent.setText(sb.toString());
        });
        mBinding.tvOutWrite.setOnClickListener(v -> {
            String newPath = getExternalFilesDir(null).getAbsolutePath() + "/testcache.txt";
            String s = "\n写入testcache.txt的第一行字符串";
            FileUtil.writeStringToFile(newPath, s);
            mBinding.tvOutWriteContent.setText(newPath);
        });
        mBinding.tvOutRead.setOnClickListener(v -> {
            String path = getExternalFilesDir(null).getAbsolutePath() + "/testcache.txt";
            String s = FileUtil.readStringFromFilePath(path);
            mBinding.tvOutReadContent.setText(s);
        });

        mBinding.tvEnvironment.setOnClickListener(v -> {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File dataDirectory = Environment.getDataDirectory();
            File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
            File rootDirectory = Environment.getRootDirectory();
            File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_DCIM);
            StringBuilder sb = new StringBuilder();
            sb.append(externalStorageDirectory.getAbsolutePath());
            sb.append("\n");
            sb.append(dataDirectory.getAbsolutePath());
            sb.append("\n");
            sb.append(downloadCacheDirectory.getAbsolutePath());
            sb.append("\n");
            sb.append(rootDirectory.getAbsolutePath());
            sb.append("\n");
            sb.append(storageDirectory.getAbsolutePath());
            sb.append("\n");
            sb.append(externalStoragePublicDirectory.getAbsolutePath());
            sb.append("\n");
            sb.append(externalFilesDir.getAbsolutePath());
            mBinding.tvEnvironmentContent.setText(sb.toString());
        });
    }

    public static void show(Context context) {
        Intent intent = new Intent(context, PrivateActivity.class);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
