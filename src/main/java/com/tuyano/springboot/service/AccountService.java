package com.tuyano.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tuyano.springboot.entity.Account;
import com.tuyano.springboot.repositories.AccountRepository;

public class AccountService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = null;
		
		if (username == null || username.isEmpty()) {
			throw new UsernameNotFoundException("�w�肵��user�͑��݂��܂���B");
		}
		
		account = accountRepo.findByUserName(username);
		if (account.isEmpty()) {
			throw new UsernameNotFoundException("�w�肵��user�͑��݂��܂���B");
		}
		
		return new AccountUserDetails(account.get());
	}

}