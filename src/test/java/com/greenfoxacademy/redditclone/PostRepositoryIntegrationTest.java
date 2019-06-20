package com.greenfoxacademy.redditclone;

import com.greenfoxacademy.redditclone.model.Post;
import com.greenfoxacademy.redditclone.repository.IPostRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PostRepositoryIntegrationTest {
  private List<String> inputList;
  private static final String SOURCEFILE = "src/test/resources/assets/sample_urls.csv";

  @Autowired
  IPostRepository postRepository;

  @Autowired
  TestEntityManager testEntityManager;

  private List<String> readFile() {
    Path path = Paths.get(SOURCEFILE);
    try {
      return Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private void flushPostsFromList(List<String> postList) {
    for (String item : postList) {
      testEntityManager.persist(new Post(item.split(";")[0], item.split(";")[1]));
      testEntityManager.flush();
    }
  }

  @Before
  public void setup() {
     inputList = readFile();
    assert inputList != null;
    flushPostsFromList(inputList);
  }

  @Test
  public void whenCallFindAllByOrderByVotesDesc_thenItsSizeEqualsToCountOfFlushedPosts() {
    List<Post> postList = postRepository.findAllByOrderByVotesDesc();
    System.out.println(postList.size());
    assertEquals(inputList.size(), postList.size());
  }
}