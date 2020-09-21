package com.khomchenko.auth.controller;

import com.khomchenko.auth.model.Experience;
import com.khomchenko.auth.repositories.ExperienceRepository;
import com.khomchenko.auth.services.ExperienceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exps")
@AllArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping
    public List<Experience> findAll(){
        return experienceService.findAll();
    }

    @GetMapping("/{id}")
    public Experience getById(@PathVariable Long id){
        return experienceService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        experienceService.deleteById(id);
    }
}
