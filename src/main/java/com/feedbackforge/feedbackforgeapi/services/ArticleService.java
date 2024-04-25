package com.feedbackforge.feedbackforgeapi.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feedbackforge.feedbackforgeapi.models.Article;
import com.feedbackforge.feedbackforgeapi.repositories.ArticleRepository;

@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;

    
    public Article findById(Long id) {
        Optional<Article> article = this.articleRepository.findById(id);
        
        return article.orElseThrow(() -> new RuntimeException("Article not found! id: " + id));
    }
    
    @Transactional
    public Article save(Article article) {
        article.setId(null);
        article = this.articleRepository.save(article);
        
        return article;
    }
    
    @Transactional
    public Article update(Article article) {
        return this.articleRepository.save(article);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.articleRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting article! id: " + id);
        }
    }

    public Iterable<Article> findAll() {
        return this.articleRepository.findAll();
    }
}
