package com.jb.news.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.jb.news.interfaceService.INewsService;
import com.jb.news.interfaces.INewsRepository;
import com.jb.news.model.News;

@Service
public class NewsService implements INewsService{
	
	@Autowired
	private INewsRepository data;
	
	@Override
	public List<News> list(String title) { 
		if (title.isEmpty()) {
			return (List<News>) data.findAll();
		}
		return (List<News>) data.findByTitle(title);		
	}
	
	@Override
	public News getNews(Integer id) {
		return data.findById(id).orElse(null);
	}

	@Override
	public News save(News n) {
		return data.save(n);
	}
	
	@Override
	public void deleteNews(Integer id) {
		data.deleteById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return data.existsById(id);
	}

	@Override
	public Page<News> listPageable(Pageable pageable) {
		return data.findAll(pageable);
	}

}
