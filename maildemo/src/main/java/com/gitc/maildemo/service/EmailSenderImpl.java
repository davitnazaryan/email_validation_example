package com.gitc.maildemo.service;

import com.gitc.maildemo.dto.MimeMailDto;
import com.gitc.maildemo.dto.SimpleMailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.File;
import java.util.Map;

@Service
public class EmailSenderImpl implements EmailSender {

  private final JavaMailSender mailSender;

  @Autowired
  public EmailSenderImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void sendSimpleMessage(@Valid SimpleMailDto mailDto) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(mailDto.getTo());
    mailMessage.setCc(mailDto.getCc());
    mailMessage.setBcc(mailDto.getBcc());
    mailMessage.setSubject(mailDto.getSubject());
    mailMessage.setText(mailDto.getText());
    this.mailSender.send(mailMessage);

  }

  @Override
  public void sendMimeMessage(MimeMailDto mailDto) throws MessagingException {
    MimeMessage mimeMessage = this.mailSender.createMimeMessage();
    MimeMessageHelper helper;
    if (mailDto.getAttachments() != null && mailDto.getAttachments().size() > 0) {
      helper = new MimeMessageHelper(mimeMessage, true);
      for (Map.Entry<String, File> fileEntry : mailDto.getAttachments().entrySet()) {
        helper.addAttachment(fileEntry.getKey(), new FileSystemResource(fileEntry.getValue()));
      }
    } else {
      helper = new MimeMessageHelper(mimeMessage, false);
    }
    helper.setTo(mailDto.getTo());
//    helper.setCc(mailDto.getCc()); //todo check if not null
//    helper.setBcc(mailDto.getBcc());
    helper.setSubject(mailDto.getSubject());
    helper.setText(mailDto.getText(), true);
    this.mailSender.send(mimeMessage);
  }
}
