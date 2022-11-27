package com.wavenet.call.center.service.subscriber;

import com.wavenet.call.center.model.Subscriber;

import java.util.List;

public interface SubscriberService {

    /**
     * Create New Subscriber
     * @param subscriber
     * @return
     */
    Subscriber addNewSubscriber(Subscriber subscriber);

    /**
     * Get All Subscribers
     * @return
     */
    List<Subscriber> findAll();
}
