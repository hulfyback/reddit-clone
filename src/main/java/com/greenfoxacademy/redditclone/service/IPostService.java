package com.greenfoxacademy.redditclone.service;

import com.greenfoxacademy.redditclone.model.Post;
import java.util.List;

public interface IPostService {

  void addPost(Post post);
  List<Post> getAllPosts();
  List<Post> getTop10Posts();

  void upvotePost(long id);

  void downvotePost(long id);
}
