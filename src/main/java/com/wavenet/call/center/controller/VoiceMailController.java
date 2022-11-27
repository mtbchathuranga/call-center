package com.wavenet.call.center.controller;

import com.wavenet.call.center.model.Subscriber;
import com.wavenet.call.center.model.VoiceMail;
import com.wavenet.call.center.model.dto.SubscriberDto;
import com.wavenet.call.center.model.dto.VoiceMailDto;
import com.wavenet.call.center.repository.StatusRepository;
import com.wavenet.call.center.repository.SubscriberRepository;
import com.wavenet.call.center.repository.VoiceMailRepository;
import com.wavenet.call.center.service.voicemail.VoiceMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wavenet")
public class VoiceMailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoiceMailController.class.getName());

    private StatusRepository statusRepository;
    private SubscriberRepository subscriberRepository;
    private VoiceMailService voiceMailService;
    private VoiceMailRepository voiceMailRepository;

    public VoiceMailController(StatusRepository statusRepository, SubscriberRepository subscriberRepository, VoiceMailService voiceMailService, VoiceMailRepository voiceMailRepository) {
        this.statusRepository = statusRepository;
        this.subscriberRepository = subscriberRepository;
        this.voiceMailService = voiceMailService;
        this.voiceMailRepository = voiceMailRepository;
    }

    @RequestMapping(value = "/voice-mail/add",method = RequestMethod.POST)
    public ResponseEntity<String> addVoiceMail(@RequestBody VoiceMailDto dto) {
        try {
            LOGGER.info("add new voice mail {} {}","/voice-mail/add",dto);
            if(dto!=null){
                VoiceMail voiceMail = new VoiceMail();
                voiceMail.setMsisdnSender(subscriberRepository.findById(dto.getMsisdnSender()).get());
                voiceMail.setMsisdnReceiver(subscriberRepository.findById(dto.getMsisdnReceiver()).get());
                voiceMail.setStatus(statusRepository.findById(dto.getStatus()).get());
                voiceMail.setPath(dto.getPath());
                VoiceMail addVoiceMail = voiceMailService.addVoiceMail(voiceMail);
                if (addVoiceMail!=null){
                    return new ResponseEntity(HttpStatus.CREATED);
                }
            }else {
                LOGGER.info("entry cant be empty {}",dto);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("entry cant be empty!");
            }
        }catch (Exception e){
            LOGGER.info("entry creation failed {}",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        LOGGER.info("entry creation failed {}",dto);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("entry creation failed!");
    }

    @RequestMapping(value = "/voice-mails",method = RequestMethod.GET)
    public List<VoiceMail> getAllSubscribers() {
        LOGGER.info("List all voice mails {}","/voice-mails");
        List<VoiceMail> voiceMails = voiceMailRepository.findAll();
        return voiceMails;
    }

}
