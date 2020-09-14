package com.ygx.ms.pojo;

import java.io.Serializable;

public class ResultBean implements Serializable {

    private String statusCode;
    private String message;


    public ResultBean(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
