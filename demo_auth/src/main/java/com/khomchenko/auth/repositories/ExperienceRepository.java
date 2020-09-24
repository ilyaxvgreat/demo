package com.khomchenko.auth.repositories;

import com.khomchenko.auth.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    List<Experience> getExperienceByUserId(Long userId);
}
