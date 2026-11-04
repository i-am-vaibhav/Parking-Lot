package com.parkinglot.adapter.out.persistence.repository;

import com.parkinglot.adapter.out.persistence.entitiy.ParkingSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataParkingSpotRepository extends JpaRepository<ParkingSpotEntity, Long> {

}
