package com.feedbackforge.feedbackforgeapi.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feedbackforge.feedbackforgeapi.models.Avaliation;
import com.feedbackforge.feedbackforgeapi.repositories.ArticleRepository;
import com.feedbackforge.feedbackforgeapi.repositories.AvaliationRepository;

@Service
public class AvaliationService {
    
    @Autowired
    private AvaliationRepository avaliationRepository;
    
    @Autowired
    private ArticleRepository articleRepository;

    
    public Avaliation findById(Long id) {
        Optional<Avaliation> Avaliation = this.avaliationRepository.findById(id);
        
        return Avaliation.orElseThrow(() -> new RuntimeException("Avaliation not found! id: " + id));
    }
    
    @Transactional
    public Avaliation save(Avaliation avaliation) {
        avaliation.setId(null);
        avaliation = this.avaliationRepository.save(avaliation);
        
        return avaliation;
    }

    @Transactional
    public Avaliation saveRate(Avaliation avaliation) {
        Avaliation aval = avaliation;
        avaliation.setAvaliated(true);
        avaliation = this.avaliationRepository.save(avaliation);

        this.articleRepository.findById(avaliation.getArticle().getId()).ifPresent(article -> {
            if(article.getNota() == 0) {
                article.setNota(aval.getNota());
            } else {
                article.setNota((article.getNota() + aval.getNota()) );
            }
            this.articleRepository.save(article);
        });
        
        return avaliation;
    }
    
    public Iterable<Avaliation> findAll() {
        return this.avaliationRepository.findAll();
    }

    public List<Avaliation> findAllByUserId(Long userId) {
        Optional<List<Avaliation>> avaliation = Optional.ofNullable(this.avaliationRepository.findAllByUserId(userId));

        return avaliation.orElseThrow(() -> new RuntimeException("Avaliations not found!"));
    }
}
