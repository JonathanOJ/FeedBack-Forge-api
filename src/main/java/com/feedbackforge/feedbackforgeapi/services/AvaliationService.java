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
    public Avaliation saveRate(Avaliation receivedAvaliation) {
        Avaliation avaliationToSave = receivedAvaliation;
        receivedAvaliation.setAvaliated(true);
        receivedAvaliation = this.avaliationRepository.save(receivedAvaliation);

        List<Avaliation> avaliations = avaliationRepository.findAllByArticleId(avaliationToSave.getArticle().getId());
        Integer finalNota = 0;
        Boolean allAvaliated = true;

        for(Avaliation avali : avaliations) {
            if (avali.getAvaliated() == false) {
                allAvaliated = false;
                break;
            }
            finalNota += avali.getNota();
        }

        if (allAvaliated) {
            avaliationToSave.getArticle().setNota(finalNota / avaliations.size());
            this.articleRepository.save(avaliationToSave.getArticle());
        }
        
        return receivedAvaliation;
    }
    
    public Iterable<Avaliation> findAll() {
        return this.avaliationRepository.findAll();
    }

    public List<Avaliation> findAllByUserId(Long userId) {
        Optional<List<Avaliation>> avaliation = Optional.ofNullable(this.avaliationRepository.findAllByUserId(userId));

        return avaliation.orElseThrow(() -> new RuntimeException("Avaliations not found!"));
    }
}
