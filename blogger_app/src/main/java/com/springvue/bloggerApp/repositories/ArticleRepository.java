package com.springvue.bloggerApp.repositories;

import com.springvue.bloggerApp.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer>{
}