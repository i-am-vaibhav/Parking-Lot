package com.parkinglot.adapter.out.persistence.entitiy;

import com.parkinglot.domain.model.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TICKET")
public class TicketEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_spot_id", nullable = false)
    private ParkingSpotEntity parkingSpot;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

}
