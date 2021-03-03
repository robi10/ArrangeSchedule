package com.tuyano.springboot.entity;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="event_name", length=50, nullable=false)
	private String eventName;
	
	@Column(name="event_info", length=200)
	private String eventInfo;
	
	@Column(name="event_time", length=500, nullable=false)
	private String eventTime;
	
	@Column(name="event_flag", length=1)
	private Boolean eventFlag;
	
	@OneToMany(mappedBy = "event")
	private Set<Entry> entries;
}