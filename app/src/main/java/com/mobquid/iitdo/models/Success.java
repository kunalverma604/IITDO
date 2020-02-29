package com.mobquid.iitdo.models;

public class Success {
    String success;
    String success_msg;

    public Success(String success, String success_msg) {
        this.success = success;
        this.success_msg = success_msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess_msg() {
        return success_msg;
    }

    public void setSuccess_msg(String success_msg) {
        this.success_msg = success_msg;
    }
}
