package com.sjz.cloudcourse.common;


public class AjaxResult {

    private Boolean success;
    private String mesg;
    private Object obj;
    private String code;

    public AjaxResult() {
    }

    public AjaxResult(Boolean success, String mesg, Object obj, String code) {
        this.success = success;
        this.mesg = mesg;
        this.obj = obj;
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
