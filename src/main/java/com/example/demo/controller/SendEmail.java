package com.example.demo.controller;

import com.example.demo.service.SendEmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;

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
    public String sendSimpleMail(String from, String to, String subject, String text) throws MessagingException {
        return sendEmailService.sendSimpleMail(from, to, subject, text);
    }

    /*//不知道为啥老是访问不了：javax.net.ssl.SSLHandshakeException
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailSend.getFrom());
        helper.setTo(emailSend.getTo());
        helper.setSubject(emailSend.getSubject());
        helper.setText(emailSend.getText().replace("customOne",emailSend.getCustomOne()),true);
        // 注意addInline()中资源名称 hello 必须与 text正文中cid:hello对应起来
        //FileSystemResource file1 = new FileSystemResource(new File(eamilImgPath));
        FileSystemResource file1 = new FileSystemResource(new File(emailSend.getImgPath()));
        helper.addInline("hello", file1);
        try {
            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
        return ActionResult.getSuccessResult("success");*/

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
