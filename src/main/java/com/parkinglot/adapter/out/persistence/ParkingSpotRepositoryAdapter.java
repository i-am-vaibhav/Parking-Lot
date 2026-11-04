package com.parkinglot.adapter.out.persistence;

import com.parkinglot.adapter.mappers.ParkingSpotEntityToDomainMapper;
import com.parkinglot.adapter.out.persistence.entitiy.ParkingSpotEntity;
import com.parkinglot.adapter.out.persistence.repository.SpringDataParkingSpotRepository;
import com.parkinglot.application.ports.out.ParkingSpotRepositoryPort;
import com.parkinglot.domain.model.ParkingSpot;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

public class ParkingSpotRepositoryAdapter implements ParkingSpotRepositoryPort {

    private final ParkingSpotEntityToDomainMapper mapper = Mappers.getMapper(ParkingSpotEntityToDomainMapper.class);

    private final SpringDataParkingSpotRepository parkingSpotRepository;

    public ParkingSpotRepositoryAdapter(SpringDataParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Override
    public void save(ParkingSpot parkingSpot) {
        parkingSpotRepository.findById(parkingSpot.spotId())
                .ifPresent(parkingSpotEntity -> {
                    mapper.updateEntity(parkingSpot, parkingSpotEntity);
                    parkingSpotRepository.save(parkingSpotEntity);
                });
    }

    @Override
    public Optional<ParkingSpot> findById(String spotId) {
        Optional<ParkingSpotEntity> parkingSpot = parkingSpotRepository.findById(Long.valueOf(spotId));
        return parkingSpot.map(mapper::toParkingSpot);
    }
}
