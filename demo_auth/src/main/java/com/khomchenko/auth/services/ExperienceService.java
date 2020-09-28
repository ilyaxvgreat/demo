package com.khomchenko.auth.services;

import com.khomchenko.auth.model.Experience;

import java.util.List;

public interface ExperienceService {
    List<Experience> findAll();

    Experience getById(Long id);

    void deleteById(Long id);

    Experience save(Experience experience);

    List<Experience> getExperienceByUserId(Long userId);
}
