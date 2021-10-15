package com.jb.news.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_user;	
	@Column(name = "suser", length = 50, nullable = false )
	private String suser;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "first_name", length = 50, nullable = false ) 
	private String first_name;
	@Column(name = "last_name", length = 50, nullable = false) 
	private String last_name;
	@Column(name = "email", nullable = false)
	private String email;
	@UpdateTimestamp
	@Column(name = "date_created")
	private Timestamp date_created;

	public User() {		
	}

	public User(int id_user, String suser, String password, String first_name, String last_name, String email,
			Timestamp date_created) {
		this.id_user = id_user;
		this.suser = suser;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.date_created = date_created;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public String getSuser() {
		return suser;
	}

	public void setSuser(String suser) {
		this.suser = suser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getDate_created() {
		return date_created;
	}

	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}

}
