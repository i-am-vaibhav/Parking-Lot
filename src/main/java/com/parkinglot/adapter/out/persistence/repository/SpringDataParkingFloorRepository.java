package com.parkinglot.adapter.out.persistence.repository;

import com.parkinglot.adapter.out.persistence.entitiy.ParkingFloorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataParkingFloorRepository extends JpaRepository<ParkingFloorEntity, Long> {

    List<ParkingFloorEntity> findByParkingLot_Id(Long parkingLotId);

}
