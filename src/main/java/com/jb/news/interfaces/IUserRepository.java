package com.jb.news.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jb.news.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	
	public User findBySuser(String suser);

}
