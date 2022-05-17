package com.example.demo.controller;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@RestController
@RequestMapping("httpsTest")
public class httpsTest {

    //下载url文件
    @GetMapping("one")
    public File getNetUrlHttp(String netUrl) {
        //对本地文件命名
        String fileName = netUrl;
        File file = null;
        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("net_url", "temp.wav");
            //下载
            urlfile = new URL(netUrl);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            //log.error("远程图片获取错误："+netUrl);
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    //下载url文件打包成zip文件
    @GetMapping("two")
    public File two(String netUrl, HttpServletResponse response) {
        File file = null;
        URL urlfile;
        //输入流
        InputStream inStream = null;
        //输出流
        OutputStream os = null;
        //zip输出流
        ZipOutputStream zos = null;
        //文件输入流
        FileInputStream fis = null;

        try {
            //前端接收
            response.reset();
            response.setContentType("application/zip;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("test" + ".zip", "UTF-8"));


            // file = File.createTempFile("net_url", "temp.wav");
            urlfile = new URL(netUrl);
            inStream = urlfile.openStream();
            zos = new ZipOutputStream(response.getOutputStream());

            ZipEntry zipEntry = new ZipEntry("voice.wav");
            zos.putNextEntry(zipEntry);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inStream.read(buffer, 0, 1024)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (zos != null) {
                    zos.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

}




