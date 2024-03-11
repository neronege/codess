package com.exam.taskNew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exam.taskNew.model.Commit;


import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Long>{
	
}
