package com.movie.ticket.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.ticket.booking.entity.Student;
import com.movie.ticket.booking.repo.StudentRepo;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/college")
public class CollegeController {

	@Autowired
	StudentRepo studentRepo;

	@PostMapping("")
	public Student saveStudent(@RequestBody Student student) {
		return studentRepo.save(student);
	}
}
