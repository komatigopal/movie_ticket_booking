package com.movie.ticket.booking.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
	private String id;
	private String type;
	private LocalDateTime inTime;
	private LocalDateTime outTime;
	private boolean parked;
	private ParkingSlot parkingSlotInformation;
	private long amount;
}
