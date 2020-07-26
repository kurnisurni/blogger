package com.springvue.bloggerApp.controllers;

import com.springvue.bloggerApp.entities.Article;
import com.springvue.bloggerApp.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Controller
@CrossOrigin

@RequestMapping("/api/v1/articles")

public class ArticleController {
  @Autowired 
  private ArticleRepository articleRepository;

  // Get All Articles
  @GetMapping("/")
  public @ResponseBody Iterable<Article> getAllArticles() {
    return articleRepository.findAll();
  }

  // Get Specific Article by ID
  @GetMapping("/{id}")
  public @ResponseBody Optional<Article> getArticle(@PathVariable("id") int id) {
    return articleRepository.findById(id);
  }

  // Add New Article
  @PostMapping("/")
  public ResponseEntity<Article> addArticle(@RequestBody Article article) {
    try {
      Article _article = articleRepository.save(
        new Article(
          article.getId(), 
          article.getTitle(), 
          article.getContent(), 
          article.getPublished()
        )
      );
      return new ResponseEntity<>(_article, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }

  // Update An Article
  @PatchMapping("/{id}")
  public ResponseEntity<Article> updateArticle(@PathVariable("id") int id, @RequestBody Article article) {
    boolean articleExist = articleRepository.existsById(id);

    if(articleExist) {
      Article existingArticle = articleRepository.findById(id).get();
      existingArticle.setTitle(article.getTitle());
      existingArticle.setContent(article.getContent());
      
      Article updatedArticle = articleRepository.save(existingArticle);
      
      return new ResponseEntity<>(updatedArticle, HttpStatus.CREATED);
    } else
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
  }

  // Delete An Article by ID
  @DeleteMapping("/{id}")
  public ResponseEntity deleteArticle(@PathVariable("id") int id) {
      boolean articleExist = articleRepository.existsById(id);

      if(articleExist) {
        articleRepository.deleteById(id);
        return new ResponseEntity<>(articleRepository.findAll(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  }

  // Sort Article by Published - Ascending Order
  @GetMapping("/publishedAsc")
  public @ResponseBody Iterable<Article> getSortedArticlesAsc() {
    return articleRepository.findAll(Sort.by(Sort.Direction.ASC, "published"));
  } 

  // Sort Article by Published - Descending Order
  @GetMapping("/publishedDesc")
  public @ResponseBody Iterable<Article> getSortedArticlesDesc() {
    return articleRepository.findAll(Sort.by(Sort.Direction.DESC, "published"));
  } 
}