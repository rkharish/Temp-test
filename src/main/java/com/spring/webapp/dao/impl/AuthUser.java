package com.spring.webapp.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.spring.webapp.dao.iAuthUser;
import com.spring.webapp.dao.entity.UserProfileEntity;



//import com.mobile.app.dao.entity.UserProfile;

@Repository("Authenticate")
@Scope("singleton")

public class AuthUser implements iAuthUser {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbct;
	
	@Override
	public String Authenticate(String username, String password){
		String message="";
		String sql="select role from login_auth_tbl where username=? and password=?";
		Object data[]=new Object[]{username,password};
		
		//JdbcTemplate is magic of spring jdbc module
		//JdbcTemplate jdbcTemplate=new JdbcTemplate(DS);
		try {
			String role=(String)jdbct.queryForObject(sql, data,String.class);
			 message="success";
		}catch (Exception e) {
			 message="fail"; 
		}
		
		/*String msg ="";
		try{
			Connection con = DS.getConnection();
			String Query = ("select * from login_auth_tbl where username=? and password=?");
			PreparedStatement ps = con.prepareStatement(Query);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				msg = "Success";
				
			}else{
				msg = "Fail";
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return msg;
	}*/
		return message;
	}
	
	@Override
	public byte[] FindImage(String UN){
		UserProfileEntity UP = null;
		String sql = "select * from login_auth_tbl where username=?";
		Object data[] = new Object[]{UN};
		try{
			UP = (UserProfileEntity)jdbct.queryForObject(sql, data,new BeanPropertyRowMapper(UserProfileEntity.class));
		
		}catch(Exception e){
			System.out.println(e);
		}
		return UP.getPhoto();
	}
	

	@CachePut(value="Spring-cache",key="#userProfile.email")
	public UserProfileEntity addUser(UserProfileEntity userProfile){
		// LobHandler LB = new DefaultLobHandler();
	//	SqlLobValue sqlLobValue=new SqlLobValue(userProfile.getPhoto(),LB);
		 int[] dataType=new int[] { Types.VARCHAR, Types.VARCHAR,
                 Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR
                ,Types.BLOB,  Types.TIMESTAMP };
		 String sql="insert into login_auth_tbl(username,password,role,companyName,email,city,title,photo,doe) values(?,?,?,?,?,?,?,?,?)";
		 Object data[]=new Object[]{userProfile.getUsername(),userProfile.getPassword(),userProfile.getRole(),userProfile.getCompanyName(),userProfile.getEmail(),userProfile.getCity(),userProfile.getTitle(),userProfile.getPhoto(),userProfile.getDoe()};
		System.out.println(userProfile.getUsername());
		jdbct.update(sql,data,dataType);
		 
		return userProfile;
	}
	
	@Cacheable(value="Spring-cache")
	public List<UserProfileEntity> DisplayProfiles(){
		
		System.out.println("!!!!!!!!!!!!!!!!!NOT IN CACHE!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!NOT IN CACHE!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!NOT IN CACHE!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!NOT IN CACHE!!!!!!!!!!!!!!!!!!!!");
		
		List<UserProfileEntity> UserProfiles=null;
		String sql = "select * from login_auth_tbl";
		try{
			UserProfiles = (List<UserProfileEntity>)jdbct.query(sql,new BeanPropertyRowMapper(UserProfileEntity.class));
		
		}catch(Exception e){
			System.out.println(e);
		}
		return UserProfiles;
	}
	
	
	@CacheEvict(value="Spring-cache",allEntries=true)
	public String deleteUser(String uid){
		String sql = "delete from login_auth_tbl where sno=?";
		int p =0;
		try{
			p=jdbct.update(sql, new Object[]{uid});
		}catch(Exception e){
			System.out.println(e);
		}
		
		return p>0? "success":"failed";
	}
	

	
	public UserProfileEntity FindProfilebyID(String UN){
		UserProfileEntity UP = null;
		String sql = "select * from login_auth_tbl where sno=?";
		Object data[] = new Object[]{UN};
		try{
			UP = (UserProfileEntity)jdbct.queryForObject(sql, data,new BeanPropertyRowMapper(UserProfileEntity.class));
		
		}catch(Exception e){
			System.out.println(e);
		}
		return UP;
	}
	
	@CacheEvict(value="Spring-cache",allEntries=true)
	public String updateProfile(UserProfileEntity userProfile) {
		String sql="update  login_auth_tbl set username=?,password=?,role=?,companyName=?,email=?,city=?,title=? where sno=?";
		Object data[]=new Object[]{userProfile.getUsername(),userProfile.getPassword(),userProfile.getRole(),userProfile.getCompanyName(),userProfile.getEmail(),userProfile.getCity(),
				userProfile.getTitle(),userProfile.getSno()};
		jdbct.update(sql,data);
		return "update";
	}
	
	public UserProfileEntity findRoleByUsername(String username) {
		UserProfileEntity userProfile=null;
		String sql="select * from login_auth_tbl where username=?";
		Object data[]=new Object[]{username};
		try {
			userProfile=(UserProfileEntity)jdbct.queryForObject(sql, data,new BeanPropertyRowMapper(UserProfileEntity.class));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return userProfile;
	}

}
