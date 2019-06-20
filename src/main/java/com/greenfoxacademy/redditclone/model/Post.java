package com.greenfoxacademy.redditclone.model;

import java.time.LocalDate;
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

  public int getVotes() {
    return votes;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getUrl() {
    return url;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public LocalDate getDate() {
    return date;
  }
}