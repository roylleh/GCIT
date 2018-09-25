package com.gcit.lms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.model.Branch;

@Repository
public interface BranchDAO extends JpaRepository<Branch, Long> {}