package com.parkinglot.application.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExitResponse {
    private Long ticketId;
    private Double amount;
    private Date entryTime;
    private Date exitTime;
}
