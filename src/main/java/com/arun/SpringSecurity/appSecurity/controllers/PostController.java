package com.arun.SpringSecurity.appSecurity.controllers;

import com.arun.SpringSecurity.appSecurity.dto.PostDto;
import com.arun.SpringSecurity.appSecurity.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody  PostDto postDto) {

        PostDto post = postService.createPost(postDto);
        return ResponseEntity.ok().body(post);
    }
}
