package com.luolin.common.exception;

public class DatabaseException extends BaseException {

    public DatabaseException(String errCode, String errContent) {
        this(errCode, errContent, null);
    }

    public DatabaseException(String errCode, String errContent, String errInformation) {
        this(errCode, errContent, errInformation, null);
    }

    public DatabaseException(String errCode, String errContent, String errInformation, String siteCode) {
        super("db-" + errCode, errContent, errInformation, siteCode);
    }
}