package com.wavenet.call.center.model.dto;

import lombok.Data;

@Data
public class SubscriberDto {
    private String name;
    private String pin;
    private int status;
}
