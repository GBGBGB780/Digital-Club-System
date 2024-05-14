package com.chinahitech.shop.service;

import com.chinahitech.shop.defineException.*;
import com.chinahitech.shop.utils.RedisUtils;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@Async
public class EmailService{
    final String requiredEmail = "mail2.sysu.edu.cn";
    public String emailHost = "smtp.qq.com";       //发送邮件的主机
    public String transportType = "smtp";           //邮件发送的协议
    public String fromUser = "Michael";           //发件人名称
    public String fromEmail = "3416825450@qq.com";  //发件人邮箱
    public String authCode = "sgcbbqgrhhiicjjj";    //发件人邮箱授权码
  //收件人邮箱
    public String subject = "验证码发送信息";           //主题信息
    String toEmail;
    String valicode;
    public static RedisUtils ru;


    public EmailService(String email_) throws EmailException{
        toEmail = email_;
        if(!checkEmail())
            throw new EmailException(toEmail);
    }

    private boolean checkEmail(){
        int atPos = toEmail.indexOf('@');
        String url = toEmail.substring(atPos + 1);
        return url.compareTo(requiredEmail) == 0;
    }

    private String getValicode() {
        Random formValicode = new Random();
        int valicode = formValicode.nextInt(1000000);
        StringBuilder valicodeStr = new StringBuilder();
        int cnt = 0;
        while(cnt != 6) {
            valicodeStr.append(valicode % 10);
            valicode /= 10;
            cnt++;
        }
        return valicodeStr.toString();
    }

    public void sendEmail() throws Exception {
        valicode = getValicode();
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        // 创建session
        Session session = Session.getInstance(prop);

        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
        ts.connect("smtp.qq.com", "3416825450@qq.com", "sgcbbqgrhhiicjjj");
        // 后面的字符是授权码，用qq密码失败了

        // 创建邮件
        Message message = createSimpleMail(session);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        boolean check = RedisUtils.set(toEmail, valicode, 300);
        if (!check) {
            throw new RedisAddException();
        }

        ts.close();
    }

    public MimeMessage createSimpleMail(Session session)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("3416825450@qq.com"));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(
                toEmail));
        // 邮件的标题
        message.setSubject("验证码发送邮件");
        // 邮件的文本内容
        message.setContent("您的验证码是:" + valicode, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }

}
