package com.tuyano.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tuyano.springboot.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
	@Query(value = "SELECT * "
	        + "FROM entry e "
	        + "JOIN account a on e.account_id = a.id "
	        + "WHERE e.account_id = ?1 ",
	        nativeQuery = true)
	List<Entry> findByAccountId(Long id);
}
