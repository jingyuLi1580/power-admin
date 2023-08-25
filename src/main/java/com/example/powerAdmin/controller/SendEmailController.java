package com.example.powerAdmin.controller;

import com.example.powerAdmin.config.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SendEmailController {
    @Autowired
    private MailUtils mailUtils;

    // mailto 发送给谁？
    private String mailto="ljy17600014929@163.com";


    @GetMapping("/email")
    public void sendEmail(){

        //  定制邮件内容
        StringBuffer content = new StringBuffer();
        content.append("亲爱的谦友，你们好~").append("\n");
        content.append("谢谢你们 能来鸟巢看我的演唱会~").append("\n");
        content.append("谢谢你们 听完我歌里的表达~").append("\n");
        content.append("感谢赴约的每一位~").append("\n");
        content.append("轻舟已过万重山～").append("\n");
        content.append("在我回来之前 见字如面").append("\n");

        //三个参数、1.接收者  2.邮件标题  3.发送的内容
        mailUtils.sendSimpleEmail(mailto,"致歌迷", String.valueOf(content));

    }
}
