package com.jb.news.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jb.news.interfaceService.INewsService;
import com.jb.news.interfaces.INewsRepository;
import com.jb.news.model.News;

@Service
public class NewsService implements INewsService{
	
	private INewsRepository data;
	
	public NewsService(INewsRepository data) {
		this.data = data;
	}
	
	public List<News> list(String title) { 
		if (title.isEmpty()) {
			return data.findAll();
		}
		return data.findByTitle(title);		
	}
	
	public News getNews(Integer id) {
		return data.findById(id).orElse(null);
	}

	public News save(News n) {
		return data.save(n);
	}
	
	public void deleteNews(Integer id) {
		data.deleteById(id);
	}

	public boolean existsById(Integer id) {
		return data.existsById(id);
	}

	public Page<News> listPageable(Pageable pageable) {
		return data.findAll(pageable);
	}

}
