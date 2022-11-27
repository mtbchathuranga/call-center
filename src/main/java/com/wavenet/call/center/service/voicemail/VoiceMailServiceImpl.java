package com.wavenet.call.center.service.voicemail;

import com.wavenet.call.center.model.VoiceMail;
import com.wavenet.call.center.repository.VoiceMailRepository;
import org.springframework.stereotype.Service;

@Service
public class VoiceMailServiceImpl implements VoiceMailService{

    private VoiceMailRepository voiceMailRepository;

    public VoiceMailServiceImpl(VoiceMailRepository voiceMailRepository) {
        this.voiceMailRepository = voiceMailRepository;
    }

    @Override
    public VoiceMail addVoiceMail(VoiceMail voiceMail) {
        return voiceMailRepository.save(voiceMail);
    }
}
