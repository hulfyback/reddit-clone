package com.greenfoxacademy.redditclone;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.greenfoxacademy.redditclone.model.Post;
import com.greenfoxacademy.redditclone.repository.IPostRepository;
import com.greenfoxacademy.redditclone.service.IPostService;
import com.greenfoxacademy.redditclone.service.PostServiceImp;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceMockingTest {
  private Post post;

  @Mock
  IPostRepository postRepository;

  @InjectMocks
  IPostService postService = new PostServiceImp();


  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    post = new Post("index", "https://index.hu");
  }

  @Test
  public void whenCallSaveMethod_thenPostSupposedToBeSavedInDatabase() {
    when(postRepository.save(any(Post.class))).thenReturn(post);
    postService.addPost(post);

    assertEquals("index", post.getTitle());

    verify(postRepository, times(1)).save(post);
  }

  @Test
  public void whenCallUpVoteMethod_thenVotesOfPostShouldBeIncreasedByOne() {
    when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
    postService.upvotePost(post.getId());

    assertEquals(1, post.getVotes());

    verify(postRepository, times(1)).findById(anyLong());
  }

  @Test
  public void whenCallDownVoteMethod_thenVotesOfPostShouldBeDecreasedByOne() {
    when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
    postService.upvotePost(post.getId());

    postService.downvotePost(post.getId());

    assertEquals(0, post.getVotes());

    verify(postRepository, times(2)).findById(anyLong());
  }

  @Test
  public void whenVotesOfPostIsZeroAndUserDownvotesIt_thenVotesOfPostShouldBeZero() {
    when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
    postService.downvotePost(post.getId());

    assertEquals(0, post.getVotes());

    verify(postRepository, times(1)).findById(post.getId());
  }
}