package com.feedbackforge.feedbackforgeapi.controller;

import com.feedbackforge.feedbackforgeapi.models.Article;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedbackforge.feedbackforgeapi.services.ArticleService;


@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/findAll/{id}")
    public List<Article> findAll(@PathVariable Long id) {
        return (List<Article>) this.articleService.findAll(id);
    }

    @GetMapping("/findAllByUsuId/{id}")
    public List<Article> findAllByUsuId(@PathVariable Long id) {
        return (List<Article>) this.articleService.findAllByUsuId(id);
    }

    @GetMapping("/{id}")
    public Article findById(@PathVariable Long id) {
        return this.articleService.findById(id);

    }

    @PostMapping("/save")
    public Article save(@RequestBody Article article) {
        return this.articleService.save(article);
    }

    @PostMapping("/update")
    public Article update(@RequestBody Article article) {
        return this.articleService.update(article);
    }
        
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.articleService.delete(id);
    }

    @GetMapping("/findAllToPublish")
    public List<Article> findAllToPublish() {
        return (List<Article>) this.articleService.findAllToPublish();
    }

    
}
