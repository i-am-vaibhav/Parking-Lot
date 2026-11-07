package com.parkinglot.adapter.in.web;

import com.parkinglot.application.model.EntryRequest;
import com.parkinglot.application.model.EntryResponse;
import com.parkinglot.application.model.ExitResponse;
import com.parkinglot.application.ports.in.EntryUseCase;
import com.parkinglot.application.ports.in.ExitUseCase;
import com.parkinglot.application.ports.in.FindAvailableSlotsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingLotController {

    private final FindAvailableSlotsUseCase findAvailableSlotsUseCase;

    private final EntryUseCase entryUseCase;

    private final ExitUseCase exitUseCase;

    public ParkingLotController(FindAvailableSlotsUseCase findAvailableSlotsUseCase, EntryUseCase entryUseCase, ExitUseCase exitUseCase) {
        this.findAvailableSlotsUseCase = findAvailableSlotsUseCase;
        this.entryUseCase = entryUseCase;
        this.exitUseCase = exitUseCase;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{parkingLotId}/findSlots")
    ResponseEntity<List<FindSlotResult>> findSlots(@PathVariable Long parkingLotId) {
        return ResponseEntity.ok(
                findAvailableSlotsUseCase.findAvailableSlots(parkingLotId)
                        .entrySet()
                        .stream()
                        .map(entry -> new FindSlotResult(
                                entry.getKey(),
                                entry.getValue()
                                        .stream()
                                        .map(spot -> new ParkingSpotVo(spot.spotId(), spot.vehicleType(), spot.available()))
                                        .toList()))
                        .toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/entry")
    ResponseEntity<EntryResponse> entry(@RequestBody EntryRequest request){
        return ResponseEntity.ofNullable(entryUseCase.entry(request));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/exit/{ticketId}")
    ResponseEntity<ExitResponse> exit(@PathVariable Long ticketId){
        return ResponseEntity.ofNullable(exitUseCase.exit(ticketId));
    }

}
