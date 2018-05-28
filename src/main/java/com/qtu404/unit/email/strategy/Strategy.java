package com.qtu404.unit.email.strategy;

import org.springframework.mail.MailSender;

public interface Strategy {
    public String getSenderAddress();

    public MailSender getMailSender();
}
