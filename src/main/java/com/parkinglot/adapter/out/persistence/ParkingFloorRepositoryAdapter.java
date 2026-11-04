package com.parkinglot.adapter.out.persistence;

import com.parkinglot.adapter.mappers.ParkingFloorEntityToDomainMapper;
import com.parkinglot.adapter.out.persistence.repository.SpringDataParkingFloorRepository;
import com.parkinglot.application.ports.out.ParkingFloorRepositoryPort;
import com.parkinglot.domain.model.ParkingFloor;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class ParkingFloorRepositoryAdapter implements ParkingFloorRepositoryPort {

    private final SpringDataParkingFloorRepository parkingFloorRepository;

    private final ParkingFloorEntityToDomainMapper mapper = Mappers.getMapper(ParkingFloorEntityToDomainMapper.class);

    public ParkingFloorRepositoryAdapter(SpringDataParkingFloorRepository parkingFloorRepository) {
        this.parkingFloorRepository = parkingFloorRepository;
    }

    @Override
    public List<ParkingFloor> findByParkingLotId(Long parkingLotId) {
        return parkingFloorRepository
                .findByParkingLot_Id(parkingLotId)
                .stream()
                .map(mapper::toParkingFloor)
                .toList();
    }
}
