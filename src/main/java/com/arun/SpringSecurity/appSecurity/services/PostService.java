package com.arun.SpringSecurity.appSecurity.services;

import com.arun.SpringSecurity.appSecurity.dto.PostDto;
import com.arun.SpringSecurity.appSecurity.entities.Post;
import com.arun.SpringSecurity.appSecurity.entities.User;
import com.arun.SpringSecurity.appSecurity.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ModelMapper modelMapper;

    private final PostRepository postRepository;

    public PostDto createPost(PostDto postDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }


}
