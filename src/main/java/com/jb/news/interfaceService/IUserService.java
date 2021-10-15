package com.jb.news.interfaceService;

import com.jb.news.model.User;

public interface IUserService {

	User getUser(String u);
	void save(User u);
	String getSecurePassword(String p);
	
}
