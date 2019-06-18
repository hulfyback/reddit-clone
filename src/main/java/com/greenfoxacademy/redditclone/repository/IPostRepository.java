package com.greenfoxacademy.redditclone.repository;

import com.greenfoxacademy.redditclone.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends CrudRepository<Post, Long> {
//  @Query(value = "SELECT FROM posts ORDER BY votes DESC", nativeQuery = true)
  List<Post> findAllByOrderByVotesDesc();

  List<Post> findTop10ByOrderByVotesDesc();
}
