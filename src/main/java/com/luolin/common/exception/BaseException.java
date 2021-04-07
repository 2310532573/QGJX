package com.luolin.common.exception;

public abstract class BaseException extends RuntimeException {

    protected String errCode;
    protected String errContent;
    protected String errInformation;
    protected String siteCode;

    protected BaseException(String errCode, String errContent) {
        super(errContent);
        this.errCode = errCode;
        this.errContent = errContent;
    }

    protected BaseException(String errCode, String errContent, String errInformation, String siteCode) {
        super(errContent);
        this.errCode = errCode;
        this.errContent = errContent;
        this.errInformation = errInformation;
        this.siteCode = siteCode;
    }

    public final String getErrCode() {
        return this.errCode;
    }


    public final String getErrContent() {
        return this.errContent;
    }

    public final String getErrInfomation() {
        return this.errInformation;
    }

    public final String getSiteCode() {
        return this.siteCode;
    }
}
