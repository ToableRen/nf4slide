package com.qtu404.present.unit.email.message;

import com.qtu404.present.unit.Parameter;
import org.springframework.mail.SimpleMailMessage;

public interface Message {
    public void setParameter(Parameter request);

    public SimpleMailMessage getSimpleMailMessage();
}
