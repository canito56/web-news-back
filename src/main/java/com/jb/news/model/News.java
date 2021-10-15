package com.jb.news.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="news")
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_news;
	@Column(name = "title", length = 50, nullable = false)
	private String title;
	@Column(name = "snews", length = 255, nullable = false )
	private String snews;
	@UpdateTimestamp
	@Column(name = "date_created")
	private Timestamp date_created;
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	public News() {		
	}

	public News(int id_news, String title, String snews, Timestamp date_created) {
		this.id_news = id_news;
		this.title = title;
		this.snews = snews;
		this.date_created = date_created;
	}

	public Integer getId_news() {
		return id_news;
	}

	public void setId_news(Integer id_news) {
		this.id_news = id_news;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSnews() {
		return snews;
	}

	public void setSnews(String snews) {
		this.snews = snews;
	}

	public Timestamp getDate_created() {
		return date_created;
	}

	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	
}
