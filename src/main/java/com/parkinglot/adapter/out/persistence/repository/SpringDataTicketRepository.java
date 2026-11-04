package com.parkinglot.adapter.out.persistence.repository;

import com.parkinglot.adapter.out.persistence.entitiy.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTicketRepository extends JpaRepository<TicketEntity, Long> {

}
