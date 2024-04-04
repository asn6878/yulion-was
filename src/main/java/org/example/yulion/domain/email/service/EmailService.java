package org.example.yulion.domain.email.service;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.example.yulion.domain.email.dto.request.EmailRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(EmailRequest request){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setTo(request.address());
            helper.setSubject("테스트 이메일 제목임");
            helper.setText(request.content());
            javaMailSender.send(mimeMessage);

        } catch (Exception e){
            // 임시 처리
            throw new IllegalArgumentException("test");
        }

    }




}
