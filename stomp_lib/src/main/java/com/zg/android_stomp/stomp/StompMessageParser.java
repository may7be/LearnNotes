package com.zg.android_stomp.stomp;

/**
 * Created by wangqi on 2017/4/20.
 */

public interface StompMessageParser<T> {
    T onParseMessage(String topic, String message);
}
