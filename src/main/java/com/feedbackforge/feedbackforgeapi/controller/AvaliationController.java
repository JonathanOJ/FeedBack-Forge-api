package com.feedbackforge.feedbackforgeapi.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedbackforge.feedbackforgeapi.models.Avaliation;
import com.feedbackforge.feedbackforgeapi.services.AvaliationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/avaliation")
@Validated
public class AvaliationController {

    private AvaliationService avaliationService;

    public AvaliationController(AvaliationService avaliationService) {
        this.avaliationService = avaliationService;
    }

    @GetMapping("/findAllByUserId/{userId}")
    public List<Avaliation> findAllByUserId(@PathVariable Long userId) {
        return this.avaliationService.findAllByUserId( userId);
    }

    @PostMapping("/save")
    public Avaliation save(@RequestBody Avaliation avaliation) {
        return this.avaliationService.save(avaliation);
    }

    @PostMapping("/saveRate")
    public Avaliation saveRate(@RequestBody Avaliation avaliation) {
        return this.avaliationService.saveRate(avaliation);
    }
    
}