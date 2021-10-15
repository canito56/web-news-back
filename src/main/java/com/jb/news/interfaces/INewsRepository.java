package com.jb.news.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jb.news.model.News;

@Repository 
public interface INewsRepository extends JpaRepository<News, Integer> {
	
	@Query("SELECT n FROM News n WHERE n.title LIKE :title||'%'")
	public List<News> findByTitle(String title);

}
