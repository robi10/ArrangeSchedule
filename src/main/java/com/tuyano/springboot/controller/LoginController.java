package com.tuyano.springboot.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuyano.springboot.entity.Account;
import com.tuyano.springboot.repositories.AccountRepository;

@Controller
public class LoginController {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@RequestMapping("/login")
	String login() {
		return "login";
	}
	
	@PostConstruct
	public void init() {
		Account account1 = new Account();
		account1.setUserName("robi");
		String password1 = "abcdef";
		String encodedPassword1 = passwordEncoder(password1);
		account1.setPassword(encodedPassword1);
		account1.setEmail("robi@sasa.ccc.co.jp");
		account1.setRole(Account.AccountRole.ROLE_USER);
		accountRepo.saveAndFlush(account1);
		
		Account account2 = new Account();
		account2.setUserName("maguro");
		String password2 = "abcdef";
		String encodedPassword2 = passwordEncoder(password2);
		account2.setPassword(encodedPassword2);
		account2.setEmail("maguro@sasa.ccc.co.jp");
		account2.setRole(Account.AccountRole.ROLE_USER);
		accountRepo.saveAndFlush(account2);
	}
	
	private String passwordEncoder(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
