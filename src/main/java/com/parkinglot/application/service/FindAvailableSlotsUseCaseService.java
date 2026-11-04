package com.parkinglot.application.service;

import com.parkinglot.application.ports.in.FindAvailableSlotsUseCase;
import com.parkinglot.application.ports.out.ParkingFloorRepositoryPort;
import com.parkinglot.domain.model.ParkingFloor;
import com.parkinglot.domain.model.ParkingSpot;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindAvailableSlotsUseCaseService implements FindAvailableSlotsUseCase {

    private final ParkingFloorRepositoryPort parkingFloorRepository;

    public FindAvailableSlotsUseCaseService(ParkingFloorRepositoryPort parkingFloorRepository) {
        this.parkingFloorRepository = parkingFloorRepository;
    }

    @Override
    public Map<Long, List<ParkingSpot>> findAvailableSlots(Long parkingLotId) {
        return parkingFloorRepository.findByParkingLotId(parkingLotId)
                .stream()
                .collect(Collectors.toMap(
                        ParkingFloor::floorNumber,
                        floor -> floor.parkingSpots().stream()
                                .filter(ParkingSpot::available)
                                .toList())
                );
    }
}
