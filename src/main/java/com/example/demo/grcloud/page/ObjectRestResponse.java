package com.example.demo.grcloud.page;

/**
 * http响应类
 * @param <T>
 */
public class ObjectRestResponse<T> extends BaseResponse {
    boolean rel;
    T data;
    public ObjectRestResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }

    public ObjectRestResponse data(T data) {
        this.setData(data);
        return this;
    }
    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
