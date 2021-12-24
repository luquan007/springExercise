package com.example.demo.service;

public interface SendEmailService  {
    String sendSimpleMail(String from, String to, String subject, String text);
}
