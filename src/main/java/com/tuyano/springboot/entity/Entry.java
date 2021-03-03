package com.tuyano.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="entry_time", length=50, nullable=false)
	private String entryTime;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
}