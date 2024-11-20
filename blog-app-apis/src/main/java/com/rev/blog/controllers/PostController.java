package com.rev.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rev.blog.payloads.PostDto;
import com.rev.blog.payloads.PostpageResponse;
import com.rev.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId){
		
		PostDto newPostDto = this.postService.createPost(postDto, userId);
		
		return new ResponseEntity<PostDto>(newPostDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostpageResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
			){
		
		PostpageResponse allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity<PostpageResponse>(allPost, HttpStatus.OK);
	}
	
	
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword){
		
		List<PostDto> searchPosts = this.postService.searchPosts(keyword);
		
		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);
	}

}
