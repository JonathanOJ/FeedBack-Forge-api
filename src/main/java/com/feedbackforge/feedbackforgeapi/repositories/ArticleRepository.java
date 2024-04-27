package com.feedbackforge.feedbackforgeapi.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedbackforge.feedbackforgeapi.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

    @Query("FROM Article a WHERE a.user.id = :usuId")
    List<Article> findAllByUsuId(Long usuId);
    
}
