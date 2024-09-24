package com.library.libraryserver;

public class Response <T>{
    private int code;
    private String message;
    private T data;
    private boolean success;


    public static <K> Response<K> newSuccess(K data){
        Response<K> response = new Response<K>();
        response.setSuccess(true);
        response.setData(data);
        response.setCode(200);
        return response;
    }
    public static Response<Void> newFailure(String message){
        Response<Void> response = new Response<Void>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setCode(200);
        return response;

    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", success=" + success +
                '}';
    }
}
