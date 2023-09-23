package com.movie.ticket.booking.entity;

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
public class ParkingSlot {
	private long id;
	private String vehicleType;
	private boolean isOccupied;
	private long rate;
}
