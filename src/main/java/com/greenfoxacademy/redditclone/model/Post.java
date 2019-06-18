package com.greenfoxacademy.redditclone.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int votes;
  private String title;
  private String url;
  private LocalDate date;

  public void increaseVotes() {
    this.votes++;
  }

  public void decreaseVotes() {
    if (this.votes > 0) {
      this.votes--;
    }
  }

  public Post() {
    this.votes = 0;
    this.title = "";
    this.url = "";
    this.date = LocalDate.now();
  }

  public Post(String title, String url) {
    this.votes = 0;
    this.title = title;
    this.url = url;
    this.date = LocalDate.now();
  }
}