package com.zg.android_net.bean;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import java.io.Serializable;

@JsonAutoDetect
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1717488731978480120L;
    private int code;
    private String errorMsg;
    private T obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "{\"code\":" + code + ",\"errorMsg\":" + errorMsg + ",\"obj\":" + obj + "}";
    }


}
