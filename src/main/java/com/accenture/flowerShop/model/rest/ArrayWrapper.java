package com.accenture.flowerShop.model.rest;

import java.util.List;

public class ArrayWrapper<T> {
    private List<T> list;
    public ArrayWrapper(List<T> in){
        this.list = in;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
