package com.gitc.maildemo.dto;

import lombok.*;

import java.io.File;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class MimeMailDto extends MailDto {

  private Map<String, File> attachments;

  @Builder
  public MimeMailDto(String subject, String text, String[] to, String[] cc, String[] bcc, Map<String, File> attachments) {
    super(subject, text, to, cc, bcc);
    this.attachments = attachments;
  }
}
