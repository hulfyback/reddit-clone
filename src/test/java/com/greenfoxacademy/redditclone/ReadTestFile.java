package com.greenfoxacademy.redditclone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadTestFile {
  private static final String SOURCEFILE = "src/test/resources/assets/sample_urls.csv";

  public static List<String> readFile() {
    Path path = Paths.get(SOURCEFILE);
    try {
      return Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}
