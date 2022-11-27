package com.wavenet.call.center.repository;

import com.wavenet.call.center.model.VoiceMail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoiceMailRepository extends CrudRepository<VoiceMail, Integer> {

    /**
     * Get all voice mails
     * @return
     */
    List<VoiceMail> findAll();
}
