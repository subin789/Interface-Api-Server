package com.ifsju.interfaceweb.service;

import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public static final String authCode = createCode();
    private MimeMessage createMessage(String toMail) throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, toMail);
        message.setFrom("kkwjdfo@gmail.com");
        message.setSubject("인증 코드 : 인터페이스 공식 사이트");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("<div style=\"max-width: 600px; text-align: center; margin: 50px auto; background-color: #ffffff;")
                .append("padding: 20px; border-radius: 5px; box-shadow: 0px 2px 10px rgba(0,0,0,0.1);\">")
                .append(" <h2 style=\"text-align: center; color: #333333; margin-top: 20px;\">인증 코드</h2>")
                .append("<p style=\"color: #666666; font-size: 16px; line-height: 1.5; margin-bottom: 20px;\">")
                .append("인터페이스 공식 홈페이지 인증코드입니다. <br>아래 인증코드를 회원가입 페이지에 입력해주세요.</p>")
                .append("<div style=\"text-align: center; margin-bottom: 30px;\">")
                .append("<span style=\"display: inline-block; padding: 10px 20px; border-radius: 5px; background-color: #007BFF; color: #ffffff; font-size: 18px;\">")
                .append(authCode)
                .append("</span> </div>")
                .append("<p style=\"color: #666666; font-size: 16px; line-height: 1.5;\">인증코드에 문제가 있는 경우 재발송을 눌러주세요.</p>")
                .append("<p style=\"color: #666666; font-size: 16px; line-height: 1.5; margin-top: 30px;\">감사합니다.</p>")
                .append(" <hr><p style=\"color: #666666; font-size: 16px; line-height: 1.5;\"> Interface 공식 사이트 운영팀 </p>")
                .append("</div>");

        message.setText(stringBuilder.toString(), "utf-8", "html");
        return message;
    }

    public String sendMessage(String toMail) throws Exception{
        MimeMessage message = createMessage(toMail);
        try {
            mailSender.send(message);
        }catch (MailException e){
            throw new IllegalArgumentException(e);
        }
        return authCode;
    }

    public static String createCode(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++){
            if (random.nextInt(3)==0){
                stringBuilder.append((char)(random.nextInt(26) + 'A'));
            }else{
                stringBuilder.append((char)(random.nextInt(10) + '0'));
            }
        }
        return stringBuilder.toString();
    }


}
