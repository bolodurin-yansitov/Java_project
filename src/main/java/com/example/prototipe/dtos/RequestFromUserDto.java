package com.example.prototipe.dtos;

import com.example.prototipe.entities.enums.ReasonOfRequesting;
import com.example.prototipe.entities.enums.StatusOfRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RequestFromUserDto {
    @Getter
    @Setter
    @NotNull
    private Long requestId;

    @Getter
    @Setter
    private byte[] file;

    @Getter
    @Setter
    private byte[] proof;

    @Getter
    @Setter
    private Short statusOfRequestCode;

    @JsonIgnore
    public StatusOfRequest getStatusOfRequest(){
        return StatusOfRequest.parse(this.statusOfRequestCode);
    }

    @JsonIgnore
    public void setStatusOfRequest(StatusOfRequest statusOfRequest){
        this.statusOfRequestCode = statusOfRequest.getValue();
    }

    @Getter
    @Setter
    private Short reasonOfRequestingCode;

    @JsonIgnore
    public ReasonOfRequesting getReasonOfRequesting(){
        return ReasonOfRequesting.parse(this.reasonOfRequestingCode);
    }

    @JsonIgnore
    public void setReasonOfRequesting(ReasonOfRequesting reasonOfRequesting){
        this.reasonOfRequestingCode = reasonOfRequesting.getValue();
    }

    @Getter
    @Setter
    @NotNull
    private Date timeOfRequesting;
}
