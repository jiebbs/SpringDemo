package work.twgj.maildemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import work.twgj.maildemo.common.ApiResponse;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;

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

    @Resource
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    private static final String TO_USER = "to@qq.com";

    /***
     * 简单邮件
     * */
    @PostMapping("/sendSimpleEmail")
    public ApiResponse sendSimpleEmail(){
        log.info("开始发送简单邮件...");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(TO_USER);
        mailMessage.setSubject("测试邮件主题");
        mailMessage.setText("测试邮件内容");
        javaMailSender.send(mailMessage);
        log.info("结束发送简单邮件...");
        return ApiResponse.success();
    }

    /**
     * HTML模版邮件
     * */
    @PostMapping("/sendHtmlEmail")
    public ApiResponse sendHtmlEmail(){
        log.info("开始发送HTML邮件...");
        MimeMessage message = null;
        try{
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(TO_USER);
            helper.setSubject("HTML模版邮件");
            StringBuilder text = new StringBuilder();
            text.append("<p style='color:#6db33f'>使用Spring Boot发送HTML格式邮件。</p>");
            // 设置发送html
            helper.setText(text.toString(),true);
            javaMailSender.send(message);
            log.info("结束发送HTML邮件...");
            return ApiResponse.success();
        }catch (Exception e){
            log.error("发送HTML模版邮件错误",e);
            return ApiResponse.error(ApiResponse.EXCEPTION_CODE,"服务器错误");
        }
    }

    /**
     * 附件邮件
     * */
    @PostMapping("/sendAttachmentEmail")
    public ApiResponse sendAttachmentEmail(){
        log.info("开始发送附件邮件...");
        MimeMessage message = null;
        try{
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(TO_USER);
            helper.setSubject("附件模版邮件");
            helper.setText("详见附件");
            ClassPathResource resource = new ClassPathResource("/static/file/附件文件.txt");
            helper.addAttachment("测试附件文件.txt",resource);
            javaMailSender.send(message);
            log.info("结束发送附件邮件...");
            return ApiResponse.success();
        }catch (Exception e){
            log.error("发送附件邮件错误",e);
            return ApiResponse.error(ApiResponse.EXCEPTION_CODE,"服务器错误");
        }
    }

    /**
     * 静态资源邮件
     * */
    @PostMapping("/sendInlineEmail")
    public ApiResponse sendInlineEmail(){
        log.info("开始发送静态资源邮件...");
        MimeMessage message = null;
        try{
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(TO_USER);
            helper.setSubject("静态资源模版邮件");
            // 这里配置引用
            helper.setText("<html><body>博客图：<img src='cid:img'/></body></html>", true);
            ClassPathResource resource = new ClassPathResource("/static/img/风景图片.jpg");
            helper.addInline("img",resource);
            javaMailSender.send(message);
            log.info("结束发送静态资源邮件...");
            return ApiResponse.success();
        }catch (Exception e){
            log.error("发送静态资源邮件错误",e);
            return ApiResponse.error(ApiResponse.EXCEPTION_CODE,"服务器错误");
        }
    }

    @PostMapping("/sendTemplateEmail/{code}")
    public ApiResponse sendTemplateEmail(@PathVariable(value = "code") String code){
        log.info("开始发送thymeleaf模版邮件...");
        MimeMessage message = null;
        try{
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(TO_USER);
            helper.setSubject("thymeleaf模版邮件");
            // 处理邮件模版
            Context context = new Context();
            context.setVariable("code",code);
            String template = templateEngine.process("emailTemplate",context);
            helper.setText(template,true);
            javaMailSender.send(message);
            log.info("结束发送thymeleaf模版邮件...");
            return ApiResponse.success();
        }catch (Exception e){
            log.error("发送thymeleaf模版邮件错误",e);
            return ApiResponse.error(ApiResponse.EXCEPTION_CODE,"服务器错误");
        }
    }
}
