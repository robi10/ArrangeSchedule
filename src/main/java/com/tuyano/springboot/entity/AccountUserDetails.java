package com.tuyano.springboot.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Account account;
	private final Collection<GrantedAuthority> authorities;
	
	public AccountUserDetails(Account account) {
		this.account = account;
		this.authorities = createAuthoritiesFrom(account);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUserName();
	}

	public Long getUserId() {
		return account.getId();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	private Collection<GrantedAuthority>
		createAuthoritiesFrom(Account account) {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
}
