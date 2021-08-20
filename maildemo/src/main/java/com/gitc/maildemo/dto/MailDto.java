package com.gitc.maildemo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class MailDto {

  protected String subject;
  protected String text;

  protected String[] to;
  protected String[] cc;
  protected String[] bcc;
}
