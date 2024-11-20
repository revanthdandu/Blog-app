package com.rev.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rev.blog.entities.Post;
import com.rev.blog.entities.User;
import com.rev.blog.exceptions.ResourceNotFoundException;
import com.rev.blog.payloads.PostDto;
import com.rev.blog.payloads.PostpageResponse;
import com.rev.blog.repositories.PostRepo;
import com.rev.blog.repositories.UserRepo;
import com.rev.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		Post newPost = this.postRepo.save(post);
		
		
		return (this.modelMapper.map(newPost, PostDto.class));
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		
		Post updatedPost = this.postRepo.save(post);
		
		PostDto updatePostDto = this.modelMapper.map(updatedPost, PostDto.class);
		
		return updatePostDto;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		
		this.postRepo.delete(post);

	}

	@Override
	public PostpageResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		Sort setSort = Sort.by(sortBy).ascending();
		
		
		if(sortDir.equalsIgnoreCase("asc")) {
			setSort = Sort.by(sortBy).ascending();
		}else {
			setSort = Sort.by(sortBy).descending();
		}

		Pageable p = PageRequest.of(pageNumber, pageSize, setSort);
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		List<Post> posts = pagePost.getContent();
		
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostpageResponse postpageResponse = new PostpageResponse();
		
		postpageResponse.setContent(postDtos);
		postpageResponse.setPageNumber(pagePost.getNumber());
		postpageResponse.setPageSize(pagePost.getSize());
		postpageResponse.setTotalElements(pagePost.getTotalElements());
		postpageResponse.setTotalPages(pagePost.getTotalPages());
		
		return postpageResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		
		return (this.modelMapper.map(post, PostDto.class));
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	
	
	
	
	
	
}
