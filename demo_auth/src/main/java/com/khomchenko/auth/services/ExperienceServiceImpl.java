package com.khomchenko.auth.services;

import com.khomchenko.auth.model.Experience;
import com.khomchenko.auth.repositories.ExperienceRepository;
import com.khomchenko.auth.services.exceptions.ExperienceAlreadyExistException;
import com.khomchenko.auth.services.exceptions.ExperienceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    @Override
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience getById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow(ExperienceNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        this.getById(id);
        experienceRepository.deleteById(id);
    }

    @Override
    public Experience save(Experience experience) {
        Optional<Experience> byId = experienceRepository.findById(experience.getId());
        if (byId.isPresent()){
            throw new ExperienceAlreadyExistException();
        }
        return experienceRepository.save(experience);
    }

    @Override
    public List<Experience> getExperienceByUserId(Long userId) {
        return experienceRepository.getExperienceByUserId(userId);
    }
}
