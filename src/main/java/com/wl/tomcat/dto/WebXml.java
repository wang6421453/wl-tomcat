package com.wl.tomcat.dto;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wl
 * @date 2019/6/17
 */
public class WebXml {

    private Map<String, String> servletMapping = new HashMap<>();

    private Map<String, String> servletClass = new HashMap<>();

    private Map<String, Servlet> servletBean = new HashMap<>();

    public Map<String, String> getServletMapping() {
        return servletMapping;
    }

    public void setServletMapping(Map<String, String> servletMapping) {
        this.servletMapping = servletMapping;
    }

    public Map<String, String> getServletClass() {
        return servletClass;
    }

    public void setServletClass(Map<String, String> servletClass) {
        this.servletClass = servletClass;
    }

    public Map<String, Servlet> getServletBean() {
        return servletBean;
    }

    public void setServletBean(Map<String, Servlet> servletBean) {
        this.servletBean = servletBean;
    }
}
