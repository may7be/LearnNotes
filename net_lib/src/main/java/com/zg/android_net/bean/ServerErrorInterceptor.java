package com.zg.android_net.bean;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Config;
import com.zg.android_net.util.BaseUtil;
import com.zg.android_net.retrofit.RetrofitException;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ZhaoKeqiang on 2017/9/1.
 * TODO:
 */

class ServerErrorInterceptor<T> implements Func1<T, Observable<T>> {
    @Override
    public Observable<T> call(T t) {
        if (t == null) {
            return Observable.error(new Throwable("Parse response failed"));
        }
        if (t instanceof JsonResult) {
            JsonResult jsonResult = (JsonResult) t;
            int code = jsonResult.getCode();
            if (code == -3) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent1 = new Intent(BaseUtil.getContext(), NetConfig.getLoginClass());
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        BaseUtil.getContext().startActivity(intent1);
                    }
                });
                return Observable.error(new RetrofitException("验证错误：" + jsonResult.getErrorMsg()));
            } else if (code < 0) {
                String debugInfo = Config.DEBUG ? "请求失败: Code:" + code + " " : "";
                return Observable.error(new RetrofitException(debugInfo + "信息: " + jsonResult.getErrorMsg()));
            }
        }
        return Observable.just(t);
    }
}
