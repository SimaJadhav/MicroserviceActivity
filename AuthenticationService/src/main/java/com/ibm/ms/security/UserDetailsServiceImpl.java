package com.ibm.ms.security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.ms.model.UserToken;
import com.ibm.ms.model.UserCredential;
import com.ibm.ms.repo.AuthenticationRepository;
import com.ibm.ms.repo.UserTokenRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	AuthenticationRepository repo;
	
	@Autowired
	UserTokenRepository userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		Optional<UserCredential>  appUser = repo.findById(username);
		if(appUser != null)	{	
				return new User(appUser.get().getUsername(), encoder.encode(appUser.get().getPassword()), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
		}
		throw new UsernameNotFoundException("Username: " + username + " not found");
		/*System.out.println("UserDetailsServiceImpl-------------------------------------");
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
		throw new UsernameNotFoundException("Username: " + username + " not found");*/
	}

	public void saveToken(String token, String name) {
		System.out.println("saveToken---------------------------------");
		userrepo.save(new UserToken(name, token));
		
	}
	
	

}
