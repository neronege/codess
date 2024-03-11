package com.exam.taskNew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exam.taskNew.model.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
	   Developer findByUsername(String username);
}