package com.ccsupport.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ccsupport.entity.User;
import com.ccsupport.models.AppUserDetails;
import com.ccsupport.repository.UserRepository;

/** 
 * <h1>This service class used to intercate with user repository</h1> 
 * This is an implementation of UserDetailsService of spring security
 * Load by user name is one of the method used for username/password authentication in spring security
 * @author  heap-space
 * @version 1.0 
 * @since   2020-04-04
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);
		user.orElseThrow(()->new UsernameNotFoundException("Not Found th user :"+userName));
		return user.map(AppUserDetails::new).get();
	}
}
