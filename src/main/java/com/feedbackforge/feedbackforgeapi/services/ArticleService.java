package com.feedbackforge.feedbackforgeapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feedbackforge.feedbackforgeapi.models.Article;
import com.feedbackforge.feedbackforgeapi.models.Avaliation;
import com.feedbackforge.feedbackforgeapi.repositories.ArticleRepository;
import com.feedbackforge.feedbackforgeapi.repositories.AvaliationRepository;

@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private AvaliationRepository avaliationRepository;

    
    public Article findById(Long id) {
        Optional<Article> article = this.articleRepository.findById(id);
        
        return article.orElseThrow(() -> new RuntimeException("Article not found! id: " + id));
    }

    public List<Article> findAllByUsuId(Long id) {
        Optional<List<Article>> articles = Optional.ofNullable(this.articleRepository.findAllByUsuId(id));
        
        return articles.orElseThrow(() -> new RuntimeException("Articles not found! id: " + id));
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

    public Iterable<Article> findAllToPublish() {
        List<Article> articles = this.articleRepository.findAll();

        List<Article> articlesToPublish = new ArrayList<Article>();

        for(Article article : articles) {
            if(article.getStatus().equals("Pending")) {
                List<Avaliation> avaliations = avaliationRepository.findAllByArticleId(article.getId());

                Boolean allAvaliated = true;
                for(Avaliation avaliation : avaliations) {
                    if (avaliation.getAvaliated() == false) {
                        allAvaliated = false;
                        break;
                    }
                }

                if (allAvaliated) {
                    articlesToPublish.add(article);
                }
            }
        }
        return articlesToPublish;
    }

}
