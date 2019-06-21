package com.greenfoxacademy.redditclone;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.greenfoxacademy.redditclone.model.Post;
import com.greenfoxacademy.redditclone.repository.IPostRepository;
import com.greenfoxacademy.redditclone.service.IPostService;
import com.greenfoxacademy.redditclone.service.PostServiceImp;
import javax.swing.Spring;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DataJdbcTest
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

    verify(postRepository, times(1)).save(post);
  }
}
