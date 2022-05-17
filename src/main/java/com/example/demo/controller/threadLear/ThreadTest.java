package com.example.demo.controller.threadLear;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class ThreadTest extends Thread {
    private String url;
    private String name;

    public ThreadTest(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为：" + name);
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest("E:\\Desktop\\1.jpg", "https://img-prod-cms-rt-microsoft-com.akamaized.net/cms/api/am/imageFileData/RE4wEaN?ver=9f5b");
        ThreadTest threadTest2 = new ThreadTest("E:\\Desktop\\2.jpg", "https://www.bing.com/th/id/ABT1858A5CC2F977FDB056C766960B4FE2153F2E4E6B2E5A6BD8E24537757FD1059?qlt=90&dpr=1.5&pid=InlineBlock");
        ThreadTest threadTest3 = new ThreadTest("E:\\Desktop\\3.jpg", "https://www.bing.com/th/id/ABT4F7D1AE84CAFF4C212C4D577A235C1F202C9A58A4695EE0E62BA7B41E672996A?qlt=90&dpr=1.5&pid=InlineBlock");

        threadTest.start();
        threadTest2.start();
        threadTest3.start();

    }
}

//下载文件
class WebDownloader {
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("errorBegin");
        }
    }

}





























