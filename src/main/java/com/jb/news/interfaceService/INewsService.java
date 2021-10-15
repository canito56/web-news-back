package com.jb.news.interfaceService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jb.news.model.News;

public interface INewsService {
	List<News> list(String title);
	News getNews(Integer id);
	boolean existsById(Integer id);
	News save(News n);
	void deleteNews(Integer id);
	Page<News> listPageable(Pageable pageable); 
}
