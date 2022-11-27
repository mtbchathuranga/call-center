package com.wavenet.call.center.service.voicemail;

import com.wavenet.call.center.model.VoiceMail;

public interface VoiceMailService {

    /**
     * Add new voice mail
     * @param voiceMail
     * @return
     */
    VoiceMail addVoiceMail(VoiceMail voiceMail);
}
