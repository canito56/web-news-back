package com.jb.news.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.jb.news.model.Message;
import com.jb.news.model.News;
import com.jb.news.service.NewsService;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "http://localhost:4200")
public class NewsController {
	@Autowired
	private NewsService serviceNews;

/*
	// Con esto no anda!!!
	@GetMapping(value="/list/{title}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<News>> list(@PathVariable("title") String title) {
		title = title == null || title.length() == 0 || title.isEmpty()? "": title;
		List<News> lnews = serviceNews.list(title);		
		return new ResponseEntity<List<News>>(lnews, HttpStatus.OK);
	}
*/	
	// Con esto anda, hay q poner /news/list?title=messi por ej, o no poner param:
	@GetMapping("/list")
	public ResponseEntity<List<News>> list(@RequestParam(value="title", defaultValue="") String title) {
		List<News> lnews = serviceNews.list(title);		
		return new ResponseEntity<List<News>>(lnews, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<News> getById(@PathVariable("id") int id) {
		if (!serviceNews.existsById(id)) 
			return new ResponseEntity(new Message("Not exists"), HttpStatus.NOT_FOUND);
		News news = serviceNews.getNews(id);
		return new ResponseEntity<News>(news, HttpStatus.OK);		
	}

	@PostMapping("/add")
	public ResponseEntity<?> save(@RequestBody News n) {
		if (n.getTitle() == "") 
			return new ResponseEntity<>(new Message("Must inform title"), HttpStatus.BAD_REQUEST);
		if (n.getSnews() == "") 
			return new ResponseEntity<>(new Message("Must inform news"), HttpStatus.BAD_REQUEST);
		if (!serviceNews.list(n.getTitle()).isEmpty()) {
			return new ResponseEntity<>(new Message("The title " + n.getTitle() +  " already exists"), HttpStatus.BAD_REQUEST);
		}
		News obj = serviceNews.save(n);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId_news()).toUri();
		return new ResponseEntity<>(new Message("News successfully added"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody News n) {
		if (n.getSnews() == "") 
			return new ResponseEntity<>(new Message("Must inform news"), HttpStatus.BAD_REQUEST);
		News un = serviceNews.getNews(id);
		if (un == null)
			return new ResponseEntity<>(new Message("News not exixt"), HttpStatus.BAD_REQUEST);
		un.setSnews(n.getSnews());
		serviceNews.save(un);
		return new ResponseEntity<>(new Message("News changed successfully"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		News un = serviceNews.getNews(id);
		if (un == null)
			return new ResponseEntity<>(new Message("News not exixt"), HttpStatus.BAD_REQUEST);
		serviceNews.deleteNews(id);
		return new ResponseEntity<>(new Message("News successfully deleted"), HttpStatus.OK);
	}

	@GetMapping("/pageable")
	public ResponseEntity<Page<News>> listPageable(Pageable pageable) throws Exception {
		Page<News> news = serviceNews.listPageable(pageable);
		return new ResponseEntity<Page<News>>(news, HttpStatus.OK);
	}
}
