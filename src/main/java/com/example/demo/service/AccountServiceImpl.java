package com.example.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.Account;
import com.example.demo.model.Config;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountServiceImpl  implements AccountService {


	private AccountRepository accountRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.print(username);
		Account account = accountRepository.findByusername(username);
		System.out.print("account: " + account);
		if (account == null) {
			System.out.print("Co vao ham nha");
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(),
				mapRolesToAuthorities(account.getConfigByRole()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Config> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toList());
	}

	@Override
	public Account save(UserRegistrationDto registrationDto, Config config) {
		Account account = new Account(registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(config));
		return accountRepository.save(account);
	}

	@Override
	public long countDuplicatedUsername(String username) {
		return accountRepository.countDuplicatedUsername(username);
	}

}
