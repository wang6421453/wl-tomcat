package com.wl.tomcat.util;

import javax.servlet.Servlet;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * @author wl
 * @date 2019/6/18
 */
public class BeanUtil {

    public static Servlet loadClass(String className, String projectPath) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException {
        URL classPath = new URL("file://" + projectPath + "/WEB-INF/classes/");
        URLClassLoader loader = new URLClassLoader(new URL[]{classPath});
        return (Servlet)loader.loadClass(className).newInstance();
    }
}
