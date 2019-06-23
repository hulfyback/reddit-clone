package com.greenfoxacademy.redditclone.controller;


import com.greenfoxacademy.redditclone.model.Post;
import com.greenfoxacademy.redditclone.service.IPostService;
import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostRestController {

  private final IPostService postService;

  public PostRestController(IPostService postService) {
    this.postService = postService;
  }

  @GetMapping("/api/posts")
  public List<Post> listAllPosts() {
    return postService.getAllPosts();
  }

  @GetMapping("/user")
  public Principal user(Principal principal) {
    return principal;
  }
}

