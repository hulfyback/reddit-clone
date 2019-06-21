package com.greenfoxacademy.redditclone;


import com.greenfoxacademy.redditclone.controller.PostRestController;
import com.greenfoxacademy.redditclone.model.Post;
import com.greenfoxacademy.redditclone.service.IPostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PostRestController.class)
public class RestControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  IPostService postService;

  @Test
  public void test() throws Exception {
    Post post = new Post();
    post.setTitle("this is just another blog post");

    List<Post> allPosts = Arrays.asList(post);

    given(postService.getAllPosts()).willReturn(allPosts);

    mockMvc.perform(get("/api/posts")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].title").value(post.getTitle()));
  }
}
