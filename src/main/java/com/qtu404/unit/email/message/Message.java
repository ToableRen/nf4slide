package com.qtu404.unit.email.message;

import com.qtu404.unit.Parameter;
import org.springframework.mail.SimpleMailMessage;

public interface Message {
    public void setParameter(Parameter request);

    public SimpleMailMessage getSimpleMailMessage();
}
