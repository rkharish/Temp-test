package com.spring.webapp.dao;

import java.util.List;

import com.spring.webapp.controller.model.UserProfile;
import com.spring.webapp.dao.entity.UserProfileEntity;



public interface iAuthUser {

	public String Authenticate(String username, String password);
	public byte[] FindImage(String UN);
	public UserProfileEntity addUser(UserProfileEntity userProfile);
	public List<UserProfileEntity> DisplayProfiles();
	public String deleteUser(String uid);
	public UserProfileEntity FindProfilebyID(String UN);
	public String updateProfile(UserProfileEntity userProfile);
	public UserProfileEntity findRoleByUsername(String username);
}
