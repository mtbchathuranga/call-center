package com.wavenet.call.center.controller;

import com.wavenet.call.center.model.Subscriber;
import com.wavenet.call.center.model.dto.SubscriberDto;
import com.wavenet.call.center.repository.StatusRepository;
import com.wavenet.call.center.repository.SubscriberRepository;
import com.wavenet.call.center.service.subscriber.SubscriberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wavenet")
public class SubscriberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberController.class.getName());

    private SubscriberService subscriberService;
    private SubscriberRepository subscriberRepository;
    private StatusRepository statusRepository;

    public SubscriberController(SubscriberService subscriberService, SubscriberRepository subscriberRepository, StatusRepository statusRepository) {
        this.subscriberService = subscriberService;
        this.subscriberRepository = subscriberRepository;
        this.statusRepository = statusRepository;
    }

    @RequestMapping(value = "/subscriber/add",method = RequestMethod.POST)
    public ResponseEntity<String> addSubscriber(@RequestBody SubscriberDto dto) {
        try {
            LOGGER.info("Add subscriber {} {}","/subscriber/add",dto);
            if(dto!=null){
                Subscriber subscriber = new Subscriber();
                subscriber.setName(dto.getName());
                subscriber.setPin(dto.getPin());
                subscriber.setStatus(statusRepository.findById(dto.getStatus()).get());
                Subscriber newSubscriber = subscriberService.addNewSubscriber(subscriber);
                if (newSubscriber!=null){
                    LOGGER.info("subscriber was created {}",newSubscriber);
                    return new ResponseEntity(HttpStatus.CREATED);
                }
            }else {
                LOGGER.info("subscriber cant be empty {}",dto);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("subscriber cant be empty!");
            }
        }catch (Exception e){
            LOGGER.error("subscriber create fail {}",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        LOGGER.info("subscriber creation failed! {}",dto);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("subscriber creation failed!");
    }

    @RequestMapping(value = "/subscribers",method = RequestMethod.GET)
    public List<Subscriber> getAllSubscribers() {
        LOGGER.info("Get all subscribers {}","/subscribers");
        List<Subscriber> subscribers = subscriberService.findAll();
        return subscribers;
    }

}
