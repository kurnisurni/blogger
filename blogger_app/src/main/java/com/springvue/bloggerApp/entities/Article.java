package com.springvue.bloggerApp.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articles")

public class Article {
  
  private Integer id;
  private String title;
  private String content;
  private Long published;

  public Article() {}

  public Article(Integer id, String title, String content, Long published) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.published = Instant.now().getEpochSecond();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Long getPublished() {
    return published;
  }

  public void setPublished(Long published) {
    this.published = published;
  }
}