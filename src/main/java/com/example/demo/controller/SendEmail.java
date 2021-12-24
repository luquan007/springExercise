package com.example.demo.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.example.demo.config.OSSConfiguration;
import com.example.demo.service.SendEmailService;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 测试相关
 */
@RestController
@RequestMapping("email")
public class SendEmail {

    @Resource
    private SendEmailService sendEmailService;

    /**
     * 发送简单的邮件
     * @param from
     * @param to
     * @param subject
     * @param text
     * @return
     */
    @GetMapping("send")
    public String sendSimpleMail(String from, String to, String subject, String text) {
        return sendEmailService.sendSimpleMail(from, to, subject, text);
    }


/*    @PostMapping("ossSend")
    public String ossSend(@RequestParam("files")MultipartFile file, String storagePath){
        String fileName = "";
        try {
            fileName = UUID.randomUUID().toString();
            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            fileName = storagePath + "/" + fileName;
            // 上传文件
            ossClient.putObject(ossConfiguration.getBucketName(), fileName, inputStream, objectMetadata);
        } catch (IOException e) {
            return e.getMessage();
        }
        return fileName;
    }*/
}
