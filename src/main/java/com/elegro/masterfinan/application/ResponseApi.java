package com.elegro.masterfinan.application;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ResponseApi<T> implements IResponseApi {

    private Boolean success;
    private String message;

    private Optional<T> data;

    public Optional<T> getData() {
        return data;
    }

    public void setData(Optional<T> data) {
        this.data = data;
    }

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
