package com.spring.webapp.service;

import java.util.List;

import com.spring.webapp.controller.model.UserProfile;
import com.spring.webapp.dao.entity.UserProfileEntity;



public interface iAuthService {

	public String Authenticate(String username, String password);
	public byte[] FindImage(String UN);
	public UserProfile addUser(UserProfile userProfile);
	public List<UserProfile> DisplayProfiles();
	public String deleteUser(String uid);
	public UserProfile FindProfilebyID(String UN);
	public String updateProfile(UserProfile userProfile);
	public UserProfile findRoleByUsername(String username);
}
