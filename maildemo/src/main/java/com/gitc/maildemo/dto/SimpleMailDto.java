package com.gitc.maildemo.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class SimpleMailDto extends MailDto {

  @Builder
  public SimpleMailDto(String subject, String text, String[] to, String[] cc, String[] bcc) {
    super(subject, text, to, cc, bcc);
  }
}
