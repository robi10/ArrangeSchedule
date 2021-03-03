package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuyano.springboot.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
