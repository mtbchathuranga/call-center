package com.wavenet.call.center.model.dto;

import com.wavenet.call.center.model.Status;
import com.wavenet.call.center.model.Subscriber;
import lombok.Data;

@Data
public class VoiceMailDto {
    private int msisdnSender;
    private int msisdnReceiver;
    private String path;
    private int status;
}
