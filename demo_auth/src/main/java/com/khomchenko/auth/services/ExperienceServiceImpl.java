package com.khomchenko.auth.services;

import com.khomchenko.auth.model.Experience;
import com.khomchenko.auth.repositories.ExperienceRepository;
import com.khomchenko.auth.services.exceptions.ExperienceAlreadyExistException;
import com.khomchenko.auth.services.exceptions.ExperienceNotFoundException;
import com.khomchenko.auth.services.exceptions.UserAlreadyExistException;
import com.khomchenko.auth.services.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        experienceRepository.findById(experience.getId())
                .orElseThrow(ExperienceAlreadyExistException::new);
        return experienceRepository.save(experience);
    }
}
