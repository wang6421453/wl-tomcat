package com.wl.tomcat;

import com.wl.tomcat.dto.Project;
import com.wl.tomcat.dto.WlRequest;
import com.wl.tomcat.dto.WlResponse;
import com.wl.tomcat.util.ProjectUtil;
import javax.servlet.Servlet;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author wl
 * @date 2019/6/13
 */
public class TomcatServer {

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    private static Map<String, Project> projectMap= new HashMap<>();

    public static void main(String[] args) throws Exception{
        String tomcatHome = System.getProperty("user.dir");
        if(tomcatHome.endsWith("bin")){
            tomcatHome = tomcatHome.substring(0, tomcatHome.lastIndexOf("bin"));
        }
        System.out.println("tomcat_home: " + tomcatHome);
        //加载项目
        projectMap = ProjectUtil.load(tomcatHome);

        //启动
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("tomcat服务启动成功...");
        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            threadPool.execute(() -> {
                WlRequest request = null;
                try {
                    request = new WlRequest(socket);
                    WlResponse response = new WlResponse(socket);
                    String context = request.getContext();
                    String path = request.getPath();
                    if(path == null){
                        return;
                    }
                    System.out.println("request:" + context + path);
                    String servletName = projectMap.get(context).getWebXml().getServletMapping().get(path);
                    Servlet servletBean = projectMap.get(context).getWebXml().getServletBean().get(servletName);
                    servletBean.service(request, response);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                   request.close();
                }
            });
        }
    }
}
