package com.mywallet.backend.app.dto;

public class ResponseDTO {

    private boolean tag;
    private String message;
    private Object data;

    public ResponseDTO(boolean tag, String message, Object data) {
        this.tag = tag;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "tag=" + tag +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
