package com.zg.android_utils.util_common;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Description
 * <p>
 * Created by Liu Huanbin
 * 2017/6/1
 */

public class ImageLoadUtil {
    @BindingAdapter({"glide", "error"})
    public static void imageLoader(ImageView v, String url, Drawable error) {
        Glide.with(v.getContext()).load(url).error(error).diskCacheStrategy(DiskCacheStrategy.SOURCE).fitCenter().crossFade().into(v);
    }
}
