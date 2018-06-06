package com.fruitday.boot.domain.error;

import com.fruitday.boot.enums.HttpResault;

public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;
    private String message;
    private String url;
    private T data;
    private Long currentTime;

    public ErrorInfo() {
    }

    public ErrorInfo(Integer code, String message, String url, T data) {
        this.code = code;
        this.message = message;
        this.url = url;
        this.data = data;
        this.currentTime = System.currentTimeMillis();
    }

    public ErrorInfo(HttpResault resault, String url, T data) {
        this.code = resault.value();
        this.message = resault.getReasonPhrase();
        this.url = url;
        this.data = data;
        this.currentTime = System.currentTimeMillis();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
