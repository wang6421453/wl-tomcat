package com.wl.tomcat.dto;


/**
 * 项目信息
 *
 * @author wl
 * @date 2019/6/17
 */
public class Project {

    private String projectName = null;

    private String projectPath = null;

    private WebXml webXml = null;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public WebXml getWebXml() {
        return webXml;
    }

    public void setWebXml(WebXml webXml) {
        this.webXml = webXml;
    }
}
