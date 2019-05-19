package com.example.prototipe.entities;


import com.example.prototipe.entities.enums.ReasonOfRequesting;
import com.example.prototipe.entities.enums.StatusOfRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
@Table(name = "REQUEST_FROM_USER")
@ToString(exclude = {"key", "user", "headman"})
public class RequestFromUser {
    @Id
    @Column(name = "REQUEST_ID")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "request_from_user_seq_gen")
    @SequenceGenerator(name = "request_from_user_seq_gen", sequenceName =
            "request_from_user_id_sequence", allocationSize = 1)
    private Long requestId;

    @Column(name = "FILE")
    @Getter
    @Setter
    @NotNull
    private byte[] file;

    @Column(name = "PROOF")
    @Getter
    @Setter
    @NotNull
    private byte[] proof;

    /*@Column(name = "HASH_FILE")
    @Getter
    @Setter
    @NotNull
    private String hashFile;

    @Column(name = "SIGN_HASH")
    @Getter
    @Setter
    @NotNull
    private String signHash;*/

    @Column(name = "CLOSE_KEY")
    @Getter
    @Setter
    @NotNull
    private byte[] closeKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLOSE_KEY", insertable = false, updatable = false)
    @Getter
    @Setter
    private Key key;

    @Column(name = "STATUS_OF_REQUEST")
    private short statusOfRequest;

    public StatusOfRequest getStatusOfRequest(){
        return StatusOfRequest.parse(this.statusOfRequest);
    }

    public void setStatusOfRequest(StatusOfRequest StatusOfRequest1){
        this.statusOfRequest = StatusOfRequest1.getValue();
    }

    @Column(name = "REASON_OF_REQUESTING")
    private short reasonOfRequesting;

    public ReasonOfRequesting getReasonOfRequesting(){
        return ReasonOfRequesting.parse(this.reasonOfRequesting);
    }

    public void setReasonOfRequesting(ReasonOfRequesting ReasonOfRequesting1){
        this.reasonOfRequesting = ReasonOfRequesting1.getValue();
    }

    @Column(name = "TIME_OF_REQUESTING")
    @Getter
    @Setter
    private LocalDateTime timeOfRequesting;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter
    @Setter
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter
    @Setter
    private Users headman;

    public RequestFromUser(Long requestId, byte[] file,
                           byte[] proof, short statusOfRequest,
                           short reasonOfRequesting,
                           LocalDateTime timeOfRequesting){
        this.requestId = requestId;
        this.file = file;
        this.proof = proof;
        this.statusOfRequest = statusOfRequest;
        this.reasonOfRequesting = reasonOfRequesting;
        this.timeOfRequesting = timeOfRequesting;
    }

    public RequestFromUser(){}
}
