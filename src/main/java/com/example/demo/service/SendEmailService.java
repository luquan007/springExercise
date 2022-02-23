package com.example.demo.service;

import javax.mail.MessagingException;

public interface SendEmailService  {
    String sendSimpleMail(String from, String to, String subject, String text) throws MessagingException;
}
