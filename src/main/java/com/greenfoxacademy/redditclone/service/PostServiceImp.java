package com.greenfoxacademy.redditclone.service;

import com.greenfoxacademy.redditclone.model.Post;
import com.greenfoxacademy.redditclone.repository.IPostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImp implements IPostService {

  @Autowired
  IPostRepository postRepository;

  @Override
  public void addPost(Post post) {
    postRepository.save(post);
  }

  @Override
  public List<Post> getAllPosts() {
    return postRepository.findAllByOrderByVotesDesc();
  }

  @Override
  public List<Post> getTop10Posts() {
    return postRepository.findTop10ByOrderByVotesDesc();
  }

  @Override
  public void upvotePost(long id) {
    Post postInDb = postRepository.findById(id).get();
    postInDb.increaseVotes();
    postRepository.save(postInDb);
  }

  @Override
  public void downvotePost(long id) {
    Post postInDb = postRepository.findById(id).get();
    postInDb.decreaseVotes();
    postRepository.save(postInDb);
  }
}
