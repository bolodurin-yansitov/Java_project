package com.example.prototipe.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "HEADMANS_REQUEST")
public class HeadmansRequest {
    @Id
    @Column(name = "REQUEST_ID")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "request_from_headman_seq_gen")
    @SequenceGenerator(name = "request_from_headman_seq_gen", sequenceName =
            "headmans_request_id_sequence", allocationSize = 1)
    private Long requestId;

    @Column(name = "FILE")
    @Getter
    @Setter
    @NotNull
    private byte[] file;

    @Column(name = "HEADMAN_ID")
    @Getter
    @Setter
    @NotNull
    private Long headmanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HEADMAN_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private Users headman;

    @Column(name = "TIME_OF_REQUESTING")
    @Getter
    @Setter
    @NotNull
    private LocalDateTime timeOfRequesting;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter
    @Setter
    private Users chairman;

    public HeadmansRequest(Long requestId, Long headmanId, byte[] file,
                           LocalDateTime timeOfRequesting){
        this.requestId = requestId;
        this.headmanId = headmanId;
        this.file = file;
        this.timeOfRequesting = timeOfRequesting;
    }
}
