package com.accenture.flowerShop.model.rest;

import java.io.Serializable;

public class JsonResponse implements Serializable {
    private String status ;
    private String message ;

    public JsonResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
