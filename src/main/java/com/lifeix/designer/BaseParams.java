package com.lifeix.designer;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;

/**
 * Created by neoyin on 2017/1/9.
 */
public class BaseParams {

    @QueryParam("deviceId")
    private String deviceId;

    @QueryParam("deviceToken")
    private String deviceToken;


    @HeaderParam("User-Agent")
    private String userAgent;

    @Context
    private Request request;

    public String getDeviceId() {
        return deviceId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Request getRequest() {
        return request;
    }

    public String getDeviceToken() {
        return deviceToken;
    }
}