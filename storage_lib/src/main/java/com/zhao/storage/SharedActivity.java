package com.zhao.storage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.zg.android_utils.util_common.FileUtil;
import com.zg.android_utils.util_common.ToastUtil;
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
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            binding.tvDirectoryContent.setText(externalStoragePublicDirectory.getAbsolutePath());
        });

        binding.tvWrite.setOnClickListener(v -> {
            if (!FileUtil.isExternalStorageWritable()) {
                ToastUtil.showToast("sd卡不能写入，应该是无SD卡");
                return;
            }
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String path = externalStoragePublicDirectory.getAbsolutePath() + "/test.txt";
            StringBuilder sb = new StringBuilder();
            sb.append("1.1.1写入Download/test.txt:");
            sb.append("\n");
            sb.append("11可以正常读写");
            sb.append("\n");
            sb.append("10无法写入，提示没有权限");
            sb.append("\n");
            sb.append("1.1.2写入Music/test.txt:");
            sb.append("\n");
            sb.append("11无法写入，提示操作被拒绝");
            sb.append("\n");
            sb.append("1.1.3写入Podcasts/test.txt:");
            sb.append("\n");
            sb.append("11无法写入，提示操作被拒绝");
            sb.append("\n");
            sb.append("1.1.4写入Ringtones/test.txt:");
            sb.append("\n");
            sb.append("11无法写入，提示操作被拒绝");
            sb.append("\n");
            sb.append("1.1.5写入Alrams/test.txt:");
            sb.append("\n");
            sb.append("11无法写入，提示操作被拒绝");
            sb.append("\n");
            sb.append("1.1.6写入Notifications/test.txt:");
            sb.append("\n");
            sb.append("11无法写入，提示操作被拒绝");
            sb.append("\n");
            sb.append("1.1.7写入Pictures/test.txt:");
            sb.append("\n");
            sb.append("11无法写入，提示操作被拒绝");
            sb.append("\n");
            sb.append("1.1.8写入Movies/test.txt:");
            sb.append("\n");
            sb.append("11无法写入，提示操作被拒绝");
            sb.append("\n");
            sb.append("1.1.9写入Dicm/test.txt:");
            sb.append("\n");
            sb.append("11无法写入，提示操作被拒绝");
            sb.append("\n");
            sb.append("1.1.10写入Documents/test.txt:");
            sb.append("\n");
            sb.append("11可以正常读写");
            sb.append("\n");
            sb.append("10无法写入，提示没有权限");
            FileUtil.writeStringToFile(path, sb.toString());
            //读取
            String s = FileUtil.readStringFromFilePath(path);
            binding.tvRead.setText(s);
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
