package com.khomchenko.auth.controller;

import com.khomchenko.auth.model.Experience;
import com.khomchenko.auth.repositories.ExperienceRepository;
import com.khomchenko.auth.services.ExperienceService;
import com.khomchenko.auth.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiences")
@AllArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @GetMapping
    public List<Experience> findAll() {
        return experienceService.findAll();
    }

    @GetMapping("/{experienceId}")
    public Experience getById(@PathVariable Long experienceId) {
        return experienceService.getById(experienceId);
    }

    @GetMapping("/users/{userId}")
    public List<Experience> getByUserId(@PathVariable Long userId) {
        return experienceService.getExperienceByUserId(userId);
    }

    @PostMapping
    public Experience saveExperience(@RequestBody Experience experience) {
        return experienceService.save(experience);
    }

    @DeleteMapping("/{experienceId}")
    public void deleteById(@PathVariable Long experienceId) {
        experienceService.deleteById(experienceId);
    }
}
