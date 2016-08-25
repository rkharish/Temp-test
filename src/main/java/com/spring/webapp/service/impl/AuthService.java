package com.spring.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.spring.webapp.*;
import com.spring.webapp.controller.model.UserProfile;
import com.spring.webapp.dao.iAuthUser;
import com.spring.webapp.dao.entity.UserProfileEntity;
import com.spring.webapp.service.iAuthService;
//import com.spring.webapp.dao.impl;

@Service("AuthService")
@Scope("singleton")

public class AuthService implements iAuthService {

	@Autowired
	@Qualifier("Authenticate")
	private iAuthUser AuthUser;
	
	@Override
	public String updateProfile(UserProfile userProfile) {
		//conversion from UserProfile to UserProfileEntity type
		UserProfileEntity entity=new UserProfileEntity();
		BeanUtils.copyProperties(userProfile, entity);
		return AuthUser.updateProfile(entity);
	}

	public UserProfile addUser(UserProfile userProfile) {
		//conversion from UserProfile to UserProfileEntity type
		UserProfileEntity entity=new UserProfileEntity();
		BeanUtils.copyProperties(userProfile, entity);
		UserProfileEntity profileEntity=AuthUser.addUser(entity);
		BeanUtils.copyProperties(profileEntity, userProfile);
		return userProfile;
	}

	
	public String authUser(String username, String password) {
		return AuthUser.Authenticate(username, password);
	}


	public byte[] FindImage(String username) {
		return AuthUser.FindImage(username);
	}

	@Override
	public UserProfile FindProfilebyID(String uid) {
		UserProfile userProfile=new UserProfile();
		UserProfileEntity profileEntity=AuthUser.FindProfilebyID(uid);
		BeanUtils.copyProperties(profileEntity, userProfile);
		return userProfile;
	}

	@Override
	public String deleteUser(String uid) {
		return AuthUser.deleteUser(uid);
	}

	@Override
	public List<UserProfile> DisplayProfiles()  {
		 List<UserProfile> profiles=new ArrayList<>();
		List<UserProfileEntity> userProfileEntity=AuthUser.DisplayProfiles();
		for(UserProfileEntity entity:userProfileEntity){
			UserProfile userProfile=new UserProfile();
			BeanUtils.copyProperties(entity, userProfile);
			profiles.add(userProfile);
		}
		return profiles;
	}

	@Override
	public String Authenticate(String username, String password) {
		return AuthUser.Authenticate(username, password);
	}
	public UserProfile findRoleByUsername(String username){
		UserProfile a = new UserProfile();
		UserProfileEntity upe  = AuthUser.findRoleByUsername(username);
		BeanUtils.copyProperties(upe, a);
		return a;
	}

}
