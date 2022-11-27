package com.wavenet.call.center.service.subscriber;

import com.wavenet.call.center.model.Subscriber;
import com.wavenet.call.center.repository.SubscriberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService{

    private SubscriberRepository subscriberRepository;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Subscriber addNewSubscriber(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @Override
    public List<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }
}
