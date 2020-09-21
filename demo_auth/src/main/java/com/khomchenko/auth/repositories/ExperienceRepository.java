package com.khomchenko.auth.repositories;

import com.khomchenko.auth.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
