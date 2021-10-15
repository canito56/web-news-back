package com.jb.news.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jb.news.interfaceService.IUserService;
import com.jb.news.interfaces.IUserRepository;
import com.jb.news.model.User;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserRepository data;
	private static String salt = "vamos river todavia CARAJO boquita puto!";
	
	@Override
	public User getUser(String u) {
		return data.findBySuser(u);
	}
	
	@Override
	public void save(User u) {
		data.save(u);
	}
	
	@Override
	public String getSecurePassword(String passwordToHash) {		
		String generatedPassword = null;
	    try {MessageDigest md = MessageDigest.getInstance("SHA-512");
	         md.update(salt.getBytes(StandardCharsets.UTF_8));
	         byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
	         StringBuilder sb = new StringBuilder();
	         for(int i=0; i< bytes.length ;i++){
	        	 sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	         }
	         generatedPassword = sb.toString();
	    } catch (NoSuchAlgorithmException e){
	    	e.printStackTrace();
	    }		    
	    return generatedPassword;
	}

}
