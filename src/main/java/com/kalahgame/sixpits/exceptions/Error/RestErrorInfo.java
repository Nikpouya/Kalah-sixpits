package com.kalahgame.sixpits.exceptions.Error;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestErrorInfo {
    public final String detail;
    public final String message;

    public RestErrorInfo(Exception ex, String detail) {
        if(!ex.getClass().getName().equalsIgnoreCase("java.lang.RuntimeException"))
            this.message = ex.getLocalizedMessage();
        else
            this.message =  "Not Available";
        this.detail = detail;
    }
}