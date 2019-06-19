package com.wl.tomcat.dto;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author wl
 * @date 2019/6/18
 */
public class WlServletOutputStream extends ServletOutputStream {

    private OutputStream outputStream;

    public WlServletOutputStream(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] bytes) throws IOException{
        outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
        outputStream.write(("Content-Length: " + bytes.length + "\r\n\r\n").getBytes());
        outputStream.write(bytes);
        outputStream.close();
    }
}
