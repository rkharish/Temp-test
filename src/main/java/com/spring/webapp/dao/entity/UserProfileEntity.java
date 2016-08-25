package com.spring.webapp.dao.entity;

import java.sql.Timestamp;

public class UserProfileEntity  {
	private String email;
	private String title;
	private String companyName;
	private String city;
	private int sno;
	private String username;
	private String password;
	private String role;
	private byte[] photo;
	private Timestamp doe;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Timestamp getDoe() {
		return doe;
	}

	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}

	@Override
	public String toString() {
		return "UserProfile [email=" + email + ", title=" + title + ", companyName=" + companyName + ", city=" + city
				+ ", sno=" + sno + ", username=" + username + ", password=" + password + ", role=" + role + ", doe=" + doe + "]";
	}

}
