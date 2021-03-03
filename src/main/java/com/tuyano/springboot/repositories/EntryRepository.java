package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuyano.springboot.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
