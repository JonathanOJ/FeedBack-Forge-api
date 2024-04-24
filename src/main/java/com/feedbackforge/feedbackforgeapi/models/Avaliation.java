package com.feedbackforge.feedbackforgeapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliation")
public class Avaliation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "article_id", nullable = false)
    private String article_id;

    @Column(name = "nota")
    private int nota;

    @Column(name = "avaliated")
    private boolean avaliated;


    public Avaliation() {
    }

    public Avaliation(String user_id, String article_id, int nota, boolean avaliated) {
        this.user_id = user_id;
        this.article_id = article_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }


}
