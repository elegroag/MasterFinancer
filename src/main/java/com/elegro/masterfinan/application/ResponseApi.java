package com.elegro.masterfinan.application;

import org.springframework.stereotype.Component;

@Component
public class ResponseApi implements IResponseApi {

    private Boolean success;
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String render() {
        return "{\"success\":"+success+", \"message\":\""+message+"\"}";
    }

    @Override
    public String toString() {
        return this.render();
    }
}
