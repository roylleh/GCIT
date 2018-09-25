package com.gcit.lms.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.model.Book;

@Repository
public interface BookDAO extends JpaRepository<Book, Long>
{
	List<Book> findByAuthorId(Long authorId);
	List<Book> findByPublisherId(Long publisherId);
}