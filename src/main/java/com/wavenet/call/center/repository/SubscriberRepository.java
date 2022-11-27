package com.wavenet.call.center.repository;

import com.wavenet.call.center.model.Subscriber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, Integer> {

    /**
     * Get All Subscribers
     * @return
     */
    List<Subscriber> findAll();
}
