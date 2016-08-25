package com.spring.webapp.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.webapp.controller.model.UserProfile;
import com.spring.webapp.dao.iAuthUser;
import com.spring.webapp.dao.entity.UserProfileEntity;
import com.spring.webapp.dao.impl.AuthUser;

//import com.mobile.app.dao.IAuthDao;
//import com.mobile.app.dao.entity.UserProfileEntity;

/**
 * A custom service for retrieving users from a custom datasource, such as a database.
 * <p>
 * This custom service must implement Spring's {@link UserDetailsService}
 */
@Transactional(readOnly = true)
@Service("UserSpringSecurityAuthProvider")
public class UserSpringSecurityAuthProvider implements UserDetailsService {
	
	@Autowired
	@Qualifier("Authenticate")
	private iAuthUser iAuthDao;
	
	/**
	 * Retrieves a user record containing the user's credentials and access. 
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		// Declare a null Spring User
		UserDetails user = null;
		try {
			UserProfileEntity entity=iAuthDao.findRoleByUsername(username);
			if(entity!=null) {
			 user =  new User( username, entity.getPassword(),
					true,
					true,
					true,
					true,
					getAuthorities(entity.getRole()));
			}else{
				UsernameNotFoundException ex=new UsernameNotFoundException("Sorry user is not in database");
				throw ex;
			}

		} catch (Exception e) {
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		return user;
	}
	
	/**
	 * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
	 * Basically, this interprets the access value whether it's for a regular user or admin.
	 * 
	 * @param access an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	 public Collection<GrantedAuthority> getAuthorities(String role) {
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
			authList.add(new GrantedAuthorityImpl(role));
			return authList;
	  }
}
