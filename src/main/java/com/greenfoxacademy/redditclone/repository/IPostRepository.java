package com.greenfoxacademy.redditclone.repository;

import com.greenfoxacademy.redditclone.model.Post;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends CrudRepository<Post, Long> {
  List<Post> findAllByOrderByVotesDesc();

  List<Post> findTop10ByOrderByVotesDesc();
}
