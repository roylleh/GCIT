package com.gcit.lms.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.model.BookCopy;
import com.gcit.lms.model.BookCopyId;

@Repository
public interface BookCopyDAO extends JpaRepository<BookCopy, BookCopyId>
{
	@Override
	Optional<BookCopy> findById(BookCopyId bookCopyId);
	List<BookCopy> findByBookCopyIdBookId(Long bookId);
	List<BookCopy> findByBookCopyIdBranchId(Long branchId);
}