package com.feedbackforge.feedbackforgeapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliation")
public class Avaliation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "nota")
    private int nota;

    @Column(name = "avaliated")
    private boolean avaliated;


    public Avaliation() {
    }

    public Avaliation(Long id, User user, Article article, int nota, boolean avaliated) {
        this.id = id;
        this.user = user;
        this.article = article;
        this.nota = nota;
        this.avaliated = avaliated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public boolean getAvaliated() {
        return avaliated;
    }

    public void setAvaliated(boolean avaliated) {
        this.avaliated = avaliated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }


}
