package com.zhao.storage;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.callbcak.CheckRequestPermissionListener;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zg.android_utils.util_common.FileUtil;
import com.zg.android_utils.util_common.ToastUtil;
import com.zhao.storage.databinding.ActivitySharedBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

        //MediaStore
        //读取相关目录：需要read权限
        binding.btnMsRead.setOnClickListener(v -> {
            SoulPermission.getInstance().checkAndRequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, new CheckRequestPermissionListener() {
                @Override
                public void onPermissionOk(Permission permission) {
                    showPhotoInfos();
                }

                @Override
                public void onPermissionDenied(Permission permission) {
                    ToastUtil.showToast("你已拒绝读取存储的权限,请到设置里自行打开");
                }
            });
        });
        //写入文件：不需要权限
        binding.btnMsWrite.setOnClickListener(v -> {
            //第一种和第二种方式其实访问到的都是/sdcard/Download目录
            Uri uri = MediaStore.Files.getContentUri("external");
//            Uri uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Files.FileColumns.MIME_TYPE, "txt");
            contentValues.put(MediaStore.Files.FileColumns.DISPLAY_NAME, "test3.txt");
            Uri insert = getContentResolver().insert(uri, contentValues);
            if (insert == null) {
                ToastUtil.showToast("insert == null");
                return;
            }
            try {
                OutputStream outputStream = getContentResolver().openOutputStream(insert);
                if (outputStream == null) {
                    ToastUtil.showToast("outputStream == null");
                    return;
                }
                String test = "第一行test";
                outputStream.write(test.getBytes());
                outputStream.flush();
                binding.tvMsWriteContent.setText(test);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //读取自己写入的文件：不需要权限
        binding.btnMsReadSelf.setOnClickListener(v -> {
            Uri uri = MediaStore.Files.getContentUri("external");
            String[] projection = {MediaStore.Files.FileColumns._ID
                    , MediaStore.Files.FileColumns.DATA
                    , MediaStore.Files.FileColumns.SIZE
                    , MediaStore.Files.FileColumns.DISPLAY_NAME};
            String selection = MediaStore.Files.FileColumns.MIME_TYPE + "=? or " + MediaStore.Files.FileColumns.MIME_TYPE + "=?";
            String[] selectionArgs = {"download/txt"};
            String sortOrder = MediaStore.Files.FileColumns.DATE_MODIFIED + " desc";
            Cursor cursor = getContentResolver().query(uri, projection, null, null, sortOrder);
            if (cursor != null) {
                StringBuilder sb = new StringBuilder();
                while (cursor.moveToNext()) {
                    // 获取路径
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
                    sb.append(path);
                    sb.append("\n");
                }
                binding.tvMsReadSelfContent.setText(sb.toString());
                cursor.close();
            }
        });

        //SAF api
        binding.tvSaf.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            // 只显示可以打开的文件
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            // 可选择所有文件类型
            intent.setType("*/*");
            // 只可选择jpeg图片
            // intent.type = "image/jpeg"
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                String path = FileUtil.getPath(this, uri);
                binding.tvSafContent.setText(MessageFormat.format("{0}\n{1}", uri.toString(), path));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 读取手机中所有图片信息
     */
    private void showPhotoInfos() {
        Observable.fromCallable(() -> {
            Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.Images.Media._ID
                    , MediaStore.Images.Media.DATA
                    , MediaStore.Images.Media.SIZE
                    , MediaStore.Images.Media.DISPLAY_NAME};
            String selection = MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?";
            String[] selectionArgs = {"image/jpeg", "image/png"};
            String sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " desc";
            Cursor mCursor = getContentResolver().query(mImageUri, projection, selection, selectionArgs, sortOrder);
            StringBuilder sb = new StringBuilder();
            if (mCursor != null) {
                int a = 0;
                while (mCursor.moveToNext() && a <= 5) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    int id = mCursor.getInt(mCursor.getColumnIndex(MediaStore.Images.Media._ID));
                    int size = mCursor.getInt(mCursor.getColumnIndex(MediaStore.Images.Media.SIZE)) / 1024;
                    String displayName = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));

                    //显示出path
                    sb.append(path);
                    sb.append("\n");
                    sb.append(id);
                    sb.append("\n");
                    a++;
                }
                mCursor.close();
            }
            return sb.toString();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY)))
                .subscribe(s -> binding.tvMsReadContent.setText(s));
    }


    public static void show(Context context) {
        Intent intent = new Intent(context, SharedActivity.class);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
