package com.ibm.ms.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.ms.model.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		System.out.println("UserDetailsServiceImpl-------------------------------------");
		final List<AppUser> users = Arrays.asList(
				new AppUser (1,"Sima",encoder.encode("12345678")),
				new AppUser(2,"Tushr",encoder.encode("87654321")));
		System.out.println("users----:"+users);
		for(AppUser appUser: users) {
			if(appUser.getUsername().equals(username)) {
				
				return new User(appUser.getUsername(), appUser.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
			}
		}
		
		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}
	
	

}
