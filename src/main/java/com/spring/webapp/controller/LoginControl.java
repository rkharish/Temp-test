package com.spring.webapp.controller;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.webapp.service.iAuthService;

@Controller
public class LoginControl {
	
	@Autowired
	@Qualifier("AuthService")
	private iAuthService User;
	
	@RequestMapping(value="auth",method=RequestMethod.POST)
	
	public String Check(HttpServletRequest RS){
		String UN = RS.getParameter("username");
		String Pass = RS.getParameter("password");
		
		RS.setAttribute("username", UN);
		
		String result = User.Authenticate(UN, Pass);
		
		if("success".equalsIgnoreCase(result)){
			 return "redirect:/mobile/profiles";
			//RS.setAttribute("ApplicationMessage","Hey "+UN+" , you are a valid user for our app. we welcome you..");
		
		}	
		else{
			RS.setAttribute("ApplicationMessage","Hey "+Pass+" , you are not a valid user for our app. Sorry try again..");
		}
		
		return "login";//login.jsp
	}
	
	@RequestMapping(value="findIMG", method=RequestMethod.GET)
	
	public void ShowImage(HttpServletRequest Request, HttpServletResponse Response){
		
		String UName = Request.getParameter("username");
		if(UName==null || UName.length()==0){
			return;
		}
		
		byte[] photo = User.FindImage(UName);
		Response.setContentType("image/jpg");
		try{
			ServletOutputStream SOS = Response.getOutputStream();
			SOS.write(photo);
			SOS.flush();
		}catch(Exception e){
			System.out.println(e);
		}
			
	}
	
	@RequestMapping(value="homePage",method = RequestMethod.GET)
    public String handleRequestInternal(Model model) throws Exception {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       //retrieving the role of the logged in user.
       Collection<? extends GrantedAuthority> grantedList=authentication.getAuthorities();
       //Here we are assuming last role present inside the list will be actual role of
       //logged in user.
       String nextPage="";
       if(grantedList!=null && grantedList.size()>0){
         Iterator<? extends GrantedAuthority> iterator=grantedList.iterator();
         if(iterator.hasNext()){
             GrantedAuthority ga=iterator.next();
             nextPage=ga.getAuthority(); //admin,user
         }
       }
       return "redirect:/mobile/profiles";
    }
	
	@RequestMapping(value="denied",method=RequestMethod.GET)
	public String denied(HttpServletRequest request) {
		return "accessDenied";
	}
	
	@RequestMapping(value="kill",method=RequestMethod.GET)
	public String kill(HttpServletRequest request) {
		return "kill";
	}
	
	
	@RequestMapping(value="invalidLogin",method=RequestMethod.GET)
	public String invalidLogin() {
		return "invalidLogin";
	}
	
	@RequestMapping(value="auth",method=RequestMethod.GET)
	public String auth(HttpServletRequest request) {
		return "login";
	}
	
}
