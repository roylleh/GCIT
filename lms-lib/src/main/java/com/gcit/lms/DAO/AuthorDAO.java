package com.gcit.lms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.model.Author;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long> {}