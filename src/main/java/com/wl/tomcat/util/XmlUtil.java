package com.wl.tomcat.util;

import com.wl.tomcat.dto.WebXml;
import com.wl.tomcat.handler.XmlParseHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * xml工具类
 * @author wl
 * @date 2019/6/17
 */
public class XmlUtil {


    /**
     * 解析xml
     * @param webxmlPath
     */
    public static WebXml parseWebXml(String webxmlPath) throws Exception{
        XmlParseHandler handler = new XmlParseHandler();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser newSAXParser = saxParserFactory.newSAXParser();
        newSAXParser.parse(webxmlPath, handler);
        return handler.getWebXml();
    }
}
