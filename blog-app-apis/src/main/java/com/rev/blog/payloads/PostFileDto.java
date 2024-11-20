package com.rev.blog.payloads;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostFileDto {
	
	private Integer postId;
	private String title;
	private String content;
	
	private MultipartFile file;
	private Date addedDate;
	private UserDto user;

}
