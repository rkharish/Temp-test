package com.spring.webapp.soap.provider;

import javax.jws.WebService;

/**
 * 
 * here we are going to write code 
 * for web service provider which will be exposed as web service
 *
 */
@WebService
public class MobileWebServiceProvider {

	 public int fact(int num) {
		 	int sum=1;
		 	for(int g=2;g<=num;g++){
		 		 sum=sum*g;
		 	}
		 	return sum;
	 }
	 
	 public String countWords(String message) {
		 	 String str[]=message.split("[ ]+");
		 	return message+" contains "+str.length+" words only.";
	 }
	
}
