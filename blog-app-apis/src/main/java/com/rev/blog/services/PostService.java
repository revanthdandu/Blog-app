package com.rev.blog.services;

import java.util.List;

import com.rev.blog.payloads.PostDto;
import com.rev.blog.payloads.PostpageResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostpageResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);
	
}
