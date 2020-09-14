package com.ygx.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@SpringBootTest
class EmailApplicationTests {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.address}")
    private String form;

    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(form);
        message.setTo("15675453227@163.com");
        message.setSubject("test");
        message.setText("合作关系初步达成，工程测试成功！请点击链接进行激活<a href='https://www.baidu.com'>激活</a>");
        mailSender.send(message);
    }


    @Test
    void sendHtml() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setFrom(form);
        message.setTo("15675453227@163.com");
        message.setSubject("test");
        message.setText("合作关系初步达成，工程测试成功！请点击链接进行激活<a href='https://www.baidu.com'>激活</a>",true);
        javaMailSender.send(mimeMessage);
    }

}
