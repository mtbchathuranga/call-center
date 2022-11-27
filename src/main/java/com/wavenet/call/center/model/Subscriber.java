package com.wavenet.call.center.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "subscriber")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "pin")
//    @JsonIgnore
    private String pin;

    @ManyToOne
    @JoinColumn(name = "status")
    private Status status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private Date modifiedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "msisdnSender", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VoiceMail> voiceMailsSender;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "msisdnReceiver", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VoiceMail> voiceMailsReceiver;

}
