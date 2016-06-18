package com.elizabeth.model;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractModel {

    public abstract String processGet(HttpServletRequest request);
    public abstract String processPost(HttpServletRequest request);
}
