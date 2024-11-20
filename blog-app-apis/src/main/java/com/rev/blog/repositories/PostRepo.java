package com.rev.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rev.blog.entities.Post;
import com.rev.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByTitleContaining(String title);
	
}
