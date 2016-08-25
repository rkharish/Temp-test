package com.spring.webapp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.spring.webapp.controller.model.UserProfile;
import com.spring.webapp.service.iAuthService;

//import com.mobile.app.dao.entity.UserProfile;

//import com.mobile.app.dao.entity.UserProfile;

//import com.mobile.app.dao.entity.UserProfile;

import spring.webapp.*;
@Controller
public class UserControl {

	@Autowired
	@Qualifier("AuthService")
	private iAuthService Auth;
	
/////////////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping(value="userProfile",method=RequestMethod.GET)
	public String UserProfilePage(Model model){
		System.out.println("Hello From controller");
		UserProfile UP = new UserProfile();
		model.addAttribute("profileCommand", UP);
		return "userProfile";
	}
	
	@RequestMapping(value="userProfile",method=RequestMethod.POST)
	public String UserProfileSubmit(@ModelAttribute ("profileCommand") UserProfile UP, Model model ){
	System.out.println(UP);
	UP.setDoe(new Timestamp(new Date().getTime()));
	Auth.addUser(UP);
	model.addAttribute("ApplicationMessage", "User profile is created successfully for username "+UP.getUsername());
	return "login";
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////	
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
             // to actually be able to convert Multipart instance to byte[]
             // we have to register a custom editor
             binder.registerCustomEditor(byte[].class,
                                new ByteArrayMultipartFileEditor());
             // now Spring knows how to handle multipart object and convert them
    }
/////////////////////////////////////////////////////////////////////////////////////////////	
	
	@RequestMapping(value="profiles",method=RequestMethod.GET)
	public String profiles(Model model){
		List<UserProfile> UP = Auth.DisplayProfiles();
		model.addAttribute("userProfiles", UP);
		return "UserDisplay";
	}
/////////////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping(value="deleteUserProfile",method=RequestMethod.GET)
	public String deleteUser(@RequestParam("uid") String uid, Model model){
		String result = Auth.deleteUser(uid);
		
		List<UserProfile> rest = Auth.DisplayProfiles();
		model.addAttribute("userProfiles", rest);
		return "UserDisplay";
	}
/////////////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping(value="editProfile",method=RequestMethod.GET)
	public String editProfilePage(@RequestParam("uid") String uid,Model model){
		UserProfile UP = Auth.FindProfilebyID(uid);
		model.addAttribute("editCommand", UP);
		return "editProfile";
	}
	
	@RequestMapping(value="editProfile",method=RequestMethod.POST)
	public String editProfileSubmit(@ModelAttribute ("eCommand") UserProfile UP, Model model ){
	System.out.println(UP);
	//UP.setDoe(new Timestamp(new Date().getTime()));
	String A = Auth.updateProfile(UP);
	model.addAttribute("profile", A);
	
	List<UserProfile> userProfiles=Auth.DisplayProfiles();
	model.addAttribute("userProfiles",userProfiles);
	return "UserDisplay";
	}
/////////////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping(value="sortUsername",method=RequestMethod.GET)
	public String sortUserProfileUserName(Model model) {
		List<UserProfile> userProfiles=Auth.DisplayProfiles();
		Collections.sort(userProfiles);
		model.addAttribute("userProfiles",userProfiles);
		return "UserDisplay";
	}
	

}
	
	
