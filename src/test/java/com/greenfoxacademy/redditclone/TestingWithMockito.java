package com.greenfoxacademy.redditclone;

import com.greenfoxacademy.redditclone.model.Post;
import com.greenfoxacademy.redditclone.repository.IPostRepository;
import com.greenfoxacademy.redditclone.service.IPostService;
import com.greenfoxacademy.redditclone.service.PostServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.Mockito.when;


@SpringBootTest
public class TestingWithMockito {

  @InjectMocks
  private IPostService postService = new PostServiceImp();

  @Mock
  IPostRepository iPostRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    List<String> urlList = ReadTestFile.readFile();
  }
  
  @Test
  public void testGetAllPosts() {
    when(iPostRepository.findAllByOrderByVotesDesc()).thenReturn(Stream
        .of(new Post("this is title", "this is url"),
            new Post("another title", "another url"))
        .collect(Collectors.toList()));
    Assert.assertEquals(2, postService.getAllPosts().size());
  }

  @Test
  public void whenCallGetTop10Posts_thenReturnTop10posts() {
    when(iPostRepository.findTop10ByOrderByVotesDesc()).thenCallRealMethod();
    assertEquals(10, postService.getTop10Posts().size());
  }
}
