package com.movie.ticket.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@GetMapping("")
	public String get() {
		return "Hello Gopal Komati";
	}
}
