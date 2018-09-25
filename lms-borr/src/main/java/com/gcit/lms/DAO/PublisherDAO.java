package com.gcit.lms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.model.Publisher;

@Repository
public interface PublisherDAO extends JpaRepository<Publisher, Long> {}