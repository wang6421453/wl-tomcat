package com.wl.tomcat.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author wl
 * @date 2019/6/17
 */
public class ZipUtil {

    /**
     * 解压文件
     * @param file
     * @throws Exception
     */
    public static void unZip(File file) throws Exception{
        String filePath = file.getPath();
        String targetPath = filePath.substring(0, filePath.lastIndexOf(".war"));
        File targetFile = new File(targetPath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }else{
            return;
        }
        ZipFile zipFile = new ZipFile(file);
        Enumeration e = zipFile.entries();
        while (e.hasMoreElements()){
            ZipEntry z = (ZipEntry) e.nextElement();
            File entryFile = new File(targetPath + File.separator + z.getName());
            if(z.isDirectory()){
                entryFile.mkdirs();
            }else {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(entryFile));
                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(z));
                int count = 0, bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                while ((count = bis.read(buffer, 0, bufferSize)) != -1){
                    bos.write(buffer, 0, count);
                }
                bos.flush();
                bos.close();
                bis.close();
            }
        }
        zipFile.close();
    }
}
