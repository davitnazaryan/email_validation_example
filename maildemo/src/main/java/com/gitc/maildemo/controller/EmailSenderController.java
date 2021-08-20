package com.gitc.maildemo.controller;

import com.gitc.maildemo.dto.MimeMailDto;
import com.gitc.maildemo.dto.SimpleMailDto;
import com.gitc.maildemo.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
public class EmailSenderController {

  private final EmailSender emailSender;

  @Autowired
  public EmailSenderController(EmailSender emailSender) {
    this.emailSender = emailSender;
  }

  @GetMapping("/api/sendSimpleMail")
  public ResponseEntity sendSimpleMail() {
    SimpleMailDto mailDto = SimpleMailDto.builder()
        .to(new String[]{"gnuni@feeder.ai"})
        .subject("Test Email")
        .text("Test Email Body")
        .build();

    this.emailSender.sendSimpleMessage(mailDto);
    return ResponseEntity.ok().build();
  }


  @GetMapping("/api/sendMimeMail")
  public ResponseEntity sendMimeMail() throws IOException, MessagingException {
    MimeMailDto mailDto = MimeMailDto.builder()
        .to(new String[]{"gnuni@feeder.ai"})
        .subject("Test Email")
        .text("<h1 style=\"color:red\">Test Email Body</h1>")
        .build();

    this.emailSender.sendMimeMessage(mailDto);
    return ResponseEntity.ok().build();
  }


  @GetMapping("/api/sendMimeMailAttachment")
  public ResponseEntity sendMimeMailWithAttachment() throws IOException, MessagingException {
    MimeMailDto mailDto = MimeMailDto.builder()
        .to(new String[]{"gnuni@feeder.ai"})
        .subject("Test Email")
        .text("<h1 style=\"color:red\">Test Email Body</h1>")
        .attachments(new HashMap<String, File>() {{
          put("odd.png", new File("/home/gnunigevorgyan/Downloads/image.png"));
        }})
        .build();

    this.emailSender.sendMimeMessage(mailDto);
    return ResponseEntity.ok().build();
  }
}
