package work.twgj.maildemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.twgj.maildemo.common.ApiResponse;

import javax.annotation.Resource;

/**
 * @author weijie.zhu
 * @date 2023/10/24 17:59
 */
@RestController
@RequestMapping("/mail")
@Slf4j
public class MailController {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @PostMapping("/sendSimpleEmail")
    public ApiResponse sendSimpleEmail(){
        log.info("开始发送简单邮件...");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo("xxx@126.com");
        mailMessage.setSubject("测试邮件主题");
        mailMessage.setText("测试邮件内容");
        javaMailSender.send(mailMessage);
        log.info("结束发送简单邮件...");
        return ApiResponse.success();
    }
}
