package com.parkinglot.adapter.in.web;


import java.util.List;

public record FindSlotResult(
     Long floor,
     List<ParkingSpotVo> parkingSpots
){

}
