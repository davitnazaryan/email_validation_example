package com.gitc.maildemo.service;

import com.gitc.maildemo.dto.MimeMailDto;
import com.gitc.maildemo.dto.SimpleMailDto;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface EmailSender {

  void sendSimpleMessage(SimpleMailDto mailDto);

  void sendMimeMessage(MimeMailDto mailDto) throws MessagingException, IOException;
}
