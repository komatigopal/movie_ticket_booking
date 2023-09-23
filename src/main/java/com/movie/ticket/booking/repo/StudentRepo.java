package com.movie.ticket.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.movie.ticket.booking.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

}
