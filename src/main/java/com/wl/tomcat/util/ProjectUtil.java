package com.wl.tomcat.util;

import com.wl.tomcat.dto.Project;
import com.wl.tomcat.dto.WebXml;
import javax.servlet.Servlet;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 项目工具类
 * @author wl
 * @date 2019/6/14
 */
public class ProjectUtil {

    public static Map<String, Project> load(String tomcatHome) throws Exception{
        String webappsPath = tomcatHome + "webapps";
        Map<String, Project> projectMap= new HashMap<>();
        File rootFile = new File(webappsPath);
        //解压war文件
        File[] wars = rootFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                boolean isFile = !file.isDirectory();
                boolean war = file.getName().endsWith(".war");
                return isFile && war;
            }
        });

        for(File war : wars){
            try {
                ZipUtil.unZip(war);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //加载所有文件夹项目
        File[] projects = rootFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        for(File projectFile : projects){
            Project project = new Project();

            String projectName = projectFile.getName();
            project.setProjectName(projectName);
            String projectPath = projectFile.getPath();
            project.setProjectPath(projectPath);

            //读取webxml
            String webxmlPath = projectFile.getPath() + File.separator + "WEB-INF" + File.separator + "web.xml";
            try {
                WebXml webXml = XmlUtil.parseWebXml(webxmlPath);
                //加载servlet到jvm
                Set<Map.Entry<String, String>> entrySet = webXml.getServletClass().entrySet();
                for(Map.Entry<String, String> entry : entrySet){
                    String servletName = entry.getKey();
                    String servletClass = entry.getValue();
                    Servlet servlet = BeanUtil.loadClass(servletClass, projectPath);
                    webXml.getServletBean().put(servletName, servlet);
                }
                project.setWebXml(webXml);
            }catch (Exception e){
                System.out.println("解析项目:" + projectName + "web.xml文件失败");
            }

            projectMap.put(projectName, project);

        }

        return projectMap;
    }

}
