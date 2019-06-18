package com.greenfoxacademy.redditclone.controller;

import com.greenfoxacademy.redditclone.model.Post;
import com.greenfoxacademy.redditclone.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {

  @Autowired
  private IPostService postService;

  @RequestMapping("/")
  public String renderPosts(Model model) {
    model.addAttribute("posts", postService.getAllPosts());
    model.addAttribute("page", 10);
    return "index";
  }

  @GetMapping("/submit")
  public String getPost(Model model) {
    model.addAttribute("posts", postService.getAllPosts());
    return "submitpost";
  }

  @PostMapping("/submit")
  public String postPost(String title, String url) {
    Post post = new Post(title, url);
    postService.addPost(post);
    return "redirect:/";
  }

  @GetMapping("/voteup")
  public String getVooteUp(Model model) {
    model.addAttribute("posts", postService.getAllPosts());
    return "index";
  }

  @PostMapping("/voteup")
  public String postVoteUp(long voteUpPostId) {
    postService.upvotePost(voteUpPostId);
    return "redirect:/";
  }
  @GetMapping("/votedown")
  public String getVooteDown(Model model) {
    model.addAttribute("posts", postService.getAllPosts());
    return "index";
  }

  @PostMapping("/votedown")
  public String postVoteDown(long voteDownPostId) {
    postService.downvotePost(voteDownPostId);
    return "redirect:/";
  }

  @GetMapping("/{page}")
  public String getNextPage(Model model, @PathVariable("page") int page) {
    model.addAttribute("page", page);
    model.addAttribute(("posts"), postService.getAllPosts());
    return "index";
  }

  @PostMapping("/{page}")
  public String postNextPage(@PathVariable("page") int page) {
    if (page < 20) {
      return "redirect:/";
    }
    return "redirect:/{page}";
  }
}
