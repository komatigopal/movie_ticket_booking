package com.movie.ticket.booking.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.ticket.booking.dto.DependencyException;
import com.movie.ticket.booking.entity.ParkingSlot;
import com.movie.ticket.booking.entity.Vehicle;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	static List<ParkingSlot> parkingSlotMap = new ArrayList<>();
	static List<Vehicle> vehicleMap = new ArrayList<>();

	@GetMapping("/insertParkingSlots")
	public List<ParkingSlot> generate() {
		for (int i = 1; i <= 5; i++) {
			String type = "hb";
			long rate = 10;
			if (i % 2 == 1) {
				type = "suv";
				rate = 20;
			}
			ParkingSlot parkingSlot = ParkingSlot.builder().id(i).vehicleType(type).isOccupied(false).rate(rate)
					.build();
			parkingSlotMap.add(parkingSlot);
		}
		return parkingSlotMap;
	}

	@GetMapping("{id}")
	public Vehicle getVehicleInfo(@PathVariable String id) {
		Vehicle vehicle = null;
		for (Vehicle v : vehicleMap) {
			if (v.getId().equals(id)) {
				return v;
			}
		}
		if (vehicle == null) {
			throw new DependencyException("vehicle not found with id - " + id);
		}
		return null;
	}

	@GetMapping("/allVehicles")
	public List<Vehicle> getAllVehicles() {
		if (vehicleMap.size() == 0) {
			throw new DependencyException("No vehicle are parked");
		}
		return vehicleMap;
	}

	@PostMapping("/parkVehicle")
	public Vehicle parkVehicle(@RequestParam String id, @RequestParam String type) {
		for (Vehicle v : vehicleMap) {
			if (v.getId().equals(id)) {
				throw new DependencyException("vehicle already parked please find the vehicle details " + v);
			}
		}
		ParkingSlot parkingSlot = null;
		for (ParkingSlot p : parkingSlotMap) {
			if (!p.isOccupied() && p.getVehicleType().equals(type)) {
				parkingSlot = p;
				p.setOccupied(true);
				break;
			}
		}
		if (parkingSlot == null) {
			for (ParkingSlot p : parkingSlotMap) {
				if (!p.isOccupied() && type.equals("hb")) {
					parkingSlot = p;
					p.setOccupied(true);
					p.setRate(10);
					break;
				}
			}
		}
		if (parkingSlot == null) {
			throw new DependencyException("slot unavaiable for in parking yard");
		}
		Vehicle vehicle = Vehicle.builder().id(id).inTime(LocalDateTime.now()).parked(true).type(type)
				.parkingSlotInformation(parkingSlot).amount(0).build();
		vehicleMap.add(vehicle);
		return vehicle;
	}

	@PutMapping("{id}")
	public Vehicle exitVehicle(@PathVariable String id) {
		Vehicle vehicle = null;
		for (int i = 0; i < vehicleMap.size(); i++) {
			Vehicle v = vehicleMap.get(i);
			if (v.getId().equals(id)) {
				v.setOutTime(LocalDateTime.now());
				long hours = ChronoUnit.SECONDS.between(v.getInTime(), v.getOutTime());
				log.info("hours - " + hours);
				v.setAmount(hours * v.getParkingSlotInformation().getRate());
				ParkingSlot parkingSlot = v.getParkingSlotInformation();
				parkingSlot.setOccupied(false);
				if (parkingSlot.getVehicleType().equals("suv")) {
					parkingSlot.setRate(20);
				}
				parkingSlotMap.remove(parkingSlot.getId());
				parkingSlotMap.add(parkingSlot);
				vehicleMap.remove(i);
				return v;
			}
		}
		if (vehicle == null) {
			throw new DependencyException("vehicle not found with id - " + id);
		}
		return null;
	}

}
