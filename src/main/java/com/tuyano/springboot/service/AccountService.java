package com.tuyano.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tuyano.springboot.entity.Account;
import com.tuyano.springboot.entity.AccountUserDetails;
import com.tuyano.springboot.repositories.AccountRepository;

public class AccountService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = null;
		
		if (username == null || username.isEmpty()) {
			throw new UsernameNotFoundException("指定したuserは存在しません。");
		}
		
		account = accountRepo.findByUserName(username);
		if (account.isEmpty()) {
			throw new UsernameNotFoundException("指定したuserは存在しません。");
		}
		
		return new AccountUserDetails(account.get());
	}
	
	@PreAuthorize("hasRole('ADMIN') or (#userName==pricipal.username)")
	public Account findOne(@P("userName") String userName) {
		return accountRepo.findByUserName(userName).get();
	}
	
	@PostAuthorize("hasRole('ADMIN') or (#userName==pricipal.username)")
	public Account findOneHoge(String userName) {
		return accountRepo.findByUserName(userName).get();
	}
}
