package com.movie.ticket.booking.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clg")
@EntityListeners(value = { AuditingEntityListener.class })
public class College {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long clgCode;
	private String name;
	private String location;
	@ManyToMany(cascade = CascadeType.ALL)
//	@ManyToMany(cascade = CascadeType.ALL)
	private List<Branch> branches;
	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany private List<Student> student;
	 */
}
