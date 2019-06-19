package com.wl.tomcat.handler;

import com.wl.tomcat.dto.WebXml;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author wl
 * @date 2019/6/18
 */
public class XmlParseHandler extends DefaultHandler{

    private WebXml webXml;

    private String currentTag;

    private String servletName = null;

    private String servletClass = null;

    private String urlPattern = null;

    public WebXml getWebXml(){
        return webXml;
    }

    @Override
    public void startDocument() throws SAXException{
        super.startDocument();
        webXml = new WebXml();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        super.startElement(uri, localName, qName, attributes);
        currentTag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if("servlet".equals(qName)){
            webXml.getServletClass().put(servletName, servletClass);
            servletName = null;
            servletClass = null;
        }else if("servlet-mapping".equals(qName)){
            webXml.getServletMapping().put(urlPattern, servletName);
            servletName = null;
            urlPattern = null;
        }
        currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        super.characters(ch, start, length);
        String value = new String(ch, start, length);
        if("servlet-name".equals(currentTag)){
            servletName = value;
        }else if("servlet-class".equals(currentTag)){
            servletClass = value;
        }else if("url-pattern".equals(currentTag)){
            urlPattern = value;
        }
    }
}
