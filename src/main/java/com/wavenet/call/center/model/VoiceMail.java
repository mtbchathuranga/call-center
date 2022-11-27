package com.wavenet.call.center.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "voiceMail")
public class VoiceMail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "msisdn_sender")
    private Subscriber msisdnSender;

    @ManyToOne
    @JoinColumn(name = "msisdn_receiver")
    private Subscriber msisdnReceiver;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "status")
    private Status status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "read_at")
    private Date modifiedAt;
}
