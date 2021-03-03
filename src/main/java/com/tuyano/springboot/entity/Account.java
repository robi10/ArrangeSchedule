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
@Table(name="account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	public enum AccountRole{ROLE_ADMIN, ROLE_USER}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length=50, nullable=false)
	private String userName;
	
	@Column(length=200, nullable=false)
	private String password;
	
	@Column(length=200, nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private AccountRole role;
	
	@OneToMany(mappedBy = "account")
	private Set<Entry> entries;
}
