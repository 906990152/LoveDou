package com.sgl.dou.model;

/**
 * Created by zhp on 2015/8/10.
 * 最外层数据
 */
public class CommonEntity<T> extends ServerStatus{

    private T data;
    private T dataset;

    public T getDataset() {
        return dataset;
    }

    public void setDataset(T dataset) {
        this.dataset = dataset;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
