package com.gcit.lms.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcit.lms.model.BookLoan;
import com.gcit.lms.model.BookLoanId;

@Repository
public interface BookLoanDAO extends JpaRepository<BookLoan, BookLoanId>
{
	@Override
	Optional<BookLoan> findById(BookLoanId bookLoanId);
	List<BookLoan> findByBookLoanIdBookId(Long bookId);
	List<BookLoan> findByBookLoanIdBranchId(Long branchId);
	List<BookLoan> findByBookLoanIdCardNo(Long cardNo);
}