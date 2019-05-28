package com.accenture.flowerShop.model.rest;

public class ObjectWrapper<T> {
    private T object;
    public ObjectWrapper(T object){
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
