package com.ojas.gcp.firstappenginetryout.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@Component
public class EmailServiceImpl {
    private static final String EMAIL_INLINEIMAGE_TEMPLATE_NAME = "html/email-inlineimage";
    private static final String MASCOT_IMAGE = "images/mascot.png";
    private static final String PNG_MIME = "image/png";

    @Qualifier("htmlTemplateResolver")
    @Autowired
    private TemplateEngine htmlTemplateEngine;

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendMemeMessage(String to, String subject) throws MessagingException {
        MimeMessage mail = emailSender.createMimeMessage();

        MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true);
        mailHelper.setTo(to);
        mailHelper.setSubject(subject);
        mailHelper.setFrom("testing@catalysed.org");



        final Context ctx = new Context(new Locale("en"));
        ctx.setVariable("Username", "Ojas Agashe");
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));



        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_INLINEIMAGE_TEMPLATE_NAME, ctx);
        mailHelper.setText(htmlContent, true);
        mailHelper.addInline("mascotImage", new ClassPathResource(MASCOT_IMAGE), PNG_MIME);

        System.out.println("HTML : "  + htmlContent);

        emailSender.send(mail);
    }
}
