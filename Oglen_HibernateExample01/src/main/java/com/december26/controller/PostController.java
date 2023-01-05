package com.december26.controller;

import java.util.Date;

import com.december26.entity.Post;
import com.december26.repository.PostRepository;

public class PostController {

	public static void main(String[] args) {
	

		PostRepository postRepository = new PostRepository();
		
		Date date = new Date();
		Post post = new Post("Drama", date);
		
		postRepository.save(post);
	}

}
