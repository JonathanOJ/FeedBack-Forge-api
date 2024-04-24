package com.feedbackforge.feedbackforgeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedbackforge.feedbackforgeapi.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

}
