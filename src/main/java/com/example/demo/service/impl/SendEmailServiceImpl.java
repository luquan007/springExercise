package com.example.demo.service.impl;

import com.example.demo.service.SendEmailService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public String sendSimpleMail(String from, String to, String subject, String text) throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人
        simpleMailMessage.setFrom(from);
        // 收件人
        simpleMailMessage.setTo(to);
        // 邮件主题
        simpleMailMessage.setSubject(subject);
        // 邮件内容
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
        return "Success";

       /* MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        //helper.setText("<html><body><img src=\"cid:hello\" >cid:luQuan</body></html>", true);
        //helper.setText("<html><body style=\"margin:0\"><div style=\"background-image: url('cid:hello');width: 1300px;height: 2300px;\"><p style=\"font-size: 40px;color: #fff;text-align: center;\">陈前</p ></div></body></html>",true);
        helper.setText("<html><body style=\"margin:0\"><div style=\"width: 1300px;height: 2300px;position: relative;\"><img src=\"cid:hello\"/><p style=\"font-size: 40px;color: #fff;position: absolute;top: 50px;left: 45%;\">"+text+"</p ></div></body></html>",true);
        // 注意addInline()中资源名称 hello 必须与 text正文中cid:hello对应起来
        FileSystemResource file1 = new FileSystemResource(new File("D:\\code\\newCode\\springExercise\\src\\main\\resources\\static\\images\\test.png"));
        helper.addInline("hello", file1);
       // helper.addInline("luQuan",);
        try {
            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
        return "success";*/
    }

}

