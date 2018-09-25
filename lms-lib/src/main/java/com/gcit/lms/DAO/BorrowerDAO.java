package com.gcit.lms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.model.Borrower;

@Repository
public interface BorrowerDAO extends JpaRepository<Borrower, Long> {}