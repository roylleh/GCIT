package com.gcit.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.DAO.AuthorDAO;
import com.gcit.lms.DAO.BookCopyDAO;
import com.gcit.lms.DAO.BookDAO;
import com.gcit.lms.DAO.BookLoanDAO;
import com.gcit.lms.DAO.BorrowerDAO;
import com.gcit.lms.DAO.BranchDAO;
import com.gcit.lms.DAO.PublisherDAO;
import com.gcit.lms.model.Book;
import com.gcit.lms.model.BookCopy;
import com.gcit.lms.model.BookCopyId;
import com.gcit.lms.model.BookLoan;
import com.gcit.lms.model.BookLoanId;
import com.gcit.lms.model.Branch;

@RestController
@RequestMapping(value = "/lms/librarian", produces = "application/json")
public class Librarian<T>
{
	//Librarians only need to be able to update book copies and branches.
	@Autowired private AuthorDAO	authors;
	@Autowired private BookDAO		books;
	@Autowired private BookCopyDAO	bookCopies;
	@Autowired private BookLoanDAO	bookLoans;
	@Autowired private BorrowerDAO	borrowers;
	@Autowired private BranchDAO	branches;
	@Autowired private PublisherDAO	publishers;
	
	
	
	
	
	/*
	 * GET
	 */
	
	@GetMapping("/{collection}")
	@SuppressWarnings("unchecked")
	public ResponseEntity< List<T> > getCollection(@PathVariable String collection)
	{
		List<T> temp = null;
		
		if( collection.equalsIgnoreCase("authors") )			temp = ( List<T> )authors.findAll();
		else if( collection.equalsIgnoreCase("books") )			temp = ( List<T> )books.findAll();
		else if( collection.equalsIgnoreCase("bookCopies") )	temp = ( List<T> )bookCopies.findAll();
		else if( collection.equalsIgnoreCase("bookLoans") )		temp = ( List<T> )bookLoans.findAll();
		else if( collection.equalsIgnoreCase("borrowers") )		temp = ( List<T> )borrowers.findAll();
		else if( collection.equalsIgnoreCase("branches") )		temp = ( List<T> )branches.findAll();
		else if( collection.equalsIgnoreCase("publishers") )	temp = ( List<T> )publishers.findAll();
		
		if(temp == null)	return new ResponseEntity< List<T> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<T> >(temp, HttpStatus.OK);
	}
	
	@GetMapping("/books/authors/{authId}")
	public ResponseEntity< List<Book> > getBooksByAuthId(@PathVariable Long authId)
	{
		List<Book> temp = books.findByAuthorId(authId);
		
		if(temp == null)	return new ResponseEntity< List<Book> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<Book> >(temp, HttpStatus.OK);
	}
	
	@GetMapping("/books/publishers/{pubId}")
	public ResponseEntity< List<Book> > getBooksByPubId(@PathVariable Long pubId)
	{
		List<Book> temp = books.findByPublisherId(pubId);
		
		if(temp == null)	return new ResponseEntity< List<Book> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<Book> >(temp, HttpStatus.OK);
	}
	
	@GetMapping("/bookCopies/books/{bookId}")
	public ResponseEntity< List<BookCopy> > getBookCopiesByBookId(@PathVariable Long bookId)
	{
		List<BookCopy> temp = bookCopies.findByBookCopyIdBookId(bookId);
		
		if(temp == null)	return new ResponseEntity< List<BookCopy> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<BookCopy> >(temp, HttpStatus.OK);
	}
	
	@GetMapping("/bookCopies/branches/{branchId}")
	public ResponseEntity< List<BookCopy> > getBookCopiesByBranchId(@PathVariable Long branchId)
	{
		List<BookCopy> temp = bookCopies.findByBookCopyIdBranchId(branchId);
		
		if(temp == null)	return new ResponseEntity< List<BookCopy> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<BookCopy> >(temp, HttpStatus.OK);
	}
	
	@GetMapping("/bookLoans/books/{bookId}")
	public ResponseEntity< List<BookLoan> > getBookLoansByBookId(@PathVariable Long bookId)
	{
		List<BookLoan> temp = bookLoans.findByBookLoanIdBookId(bookId);
		
		if(temp == null)	return new ResponseEntity< List<BookLoan> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<BookLoan> >(temp, HttpStatus.OK);
	}
	
	@GetMapping("/bookLoans/branches/{branchId}")
	public ResponseEntity< List<BookLoan> > getBookLoansByBranchId(@PathVariable Long branchId)
	{
		List<BookLoan> temp = bookLoans.findByBookLoanIdBranchId(branchId);
		
		if(temp == null)	return new ResponseEntity< List<BookLoan> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<BookLoan> >(temp, HttpStatus.OK);
	}
	
	@GetMapping("/bookLoans/cardNo/{cardNo}")
	public ResponseEntity< List<BookLoan> > getBookLoansByCardNo(@PathVariable Long cardNo)
	{
		List<BookLoan> temp = bookLoans.findByBookLoanIdCardNo(cardNo);
		
		if(temp == null)	return new ResponseEntity< List<BookLoan> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<BookLoan> >(temp, HttpStatus.OK);
	}
	
	@GetMapping("/{collection}/{id}")
	@SuppressWarnings("unchecked")
	public ResponseEntity<T> getElement(@PathVariable String collection, @PathVariable Long id)
	{
		T temp = null;
		
		if( collection.equalsIgnoreCase("authors") && authors.existsById(id) )				temp = (T)authors.findById(id).get();
		else if( collection.equalsIgnoreCase("books") && books.existsById(id) )				temp = (T)books.findById(id).get();
		else if( collection.equalsIgnoreCase("borrowers") && borrowers.existsById(id) )		temp = (T)borrowers.findById(id).get();
		else if( collection.equalsIgnoreCase("branches") && branches.existsById(id) )		temp = (T)branches.findById(id).get();
		else if( collection.equalsIgnoreCase("publishers") && publishers.existsById(id) )	temp = (T)publishers.findById(id).get();
		
		if(temp == null)	return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity<T>(temp, HttpStatus.OK);
	}
	
	@GetMapping("/bookCopies/books/{bookId}/branches/{branchId}")
	public ResponseEntity<BookCopy> getBookCopy(@PathVariable Long bookId, @PathVariable Long branchId)
	{
		BookCopy temp = null;
		BookCopyId bookCopyId = new BookCopyId(bookId, branchId);
		
		if( bookCopies.existsById(bookCopyId) )
			temp = bookCopies.findById(bookCopyId).get();
		
		if(temp == null)	return new ResponseEntity<BookCopy>(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity<BookCopy>(temp, HttpStatus.OK);
	}
	
	@GetMapping("/bookLoans/books/{bookId}/branches/{branchId}/cardNo/{cardNo}")
	public ResponseEntity<BookLoan> getBookLoan(@PathVariable Long bookId, @PathVariable Long branchId, @PathVariable Long cardNo)
	{
		BookLoan temp = null;
		BookLoanId bookLoanId = new BookLoanId(bookId, branchId, cardNo);
		
		if( bookLoans.existsById(bookLoanId) )
			temp = bookLoans.findById(bookLoanId).get();
		
		if(temp == null)	return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity<BookLoan>(temp, HttpStatus.OK);
	}
	
	
	
	
	
	/*
	 * PUT
	 */
	
	@PutMapping(value = "/bookCopies", consumes = "application/json")
	public ResponseEntity<BookCopy> updateBookCopy(@RequestBody BookCopy bookCopy)
	{
		BookCopy temp = null;
		if( bookCopies.existsById(bookCopy.getBookCopyId()) )
			temp = bookCopies.findById( bookCopy.getBookCopyId() ).get();
		
		if(temp == null)
			return new ResponseEntity<BookCopy>(HttpStatus.NOT_FOUND);
		
		temp.setNoOfCopies( bookCopy.getNoOfCopies() );
		
		temp = bookCopies.save(temp);
		return new ResponseEntity<BookCopy>(temp, HttpStatus.OK);
	}
	
	@PutMapping(value = "/branches", consumes = "application/json")
	public ResponseEntity<Branch> updateBranch(@RequestBody Branch branch)
	{
		Branch temp = null;
		if( branches.existsById(branch.getBranchId()) )
			temp = branches.findById( branch.getBranchId() ).get();
		
		if(temp == null)
			return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
		
		temp.setBranchName( branch.getBranchName() );
		temp.setBranchAddress( branch.getBranchAddress() );
		
		temp = branches.save(temp);
		return new ResponseEntity<Branch>(temp, HttpStatus.OK);
	}
}