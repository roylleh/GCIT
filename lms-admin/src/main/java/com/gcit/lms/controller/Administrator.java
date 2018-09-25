package com.gcit.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.gcit.lms.model.Author;
import com.gcit.lms.model.Book;
import com.gcit.lms.model.BookCopy;
import com.gcit.lms.model.BookCopyId;
import com.gcit.lms.model.BookLoan;
import com.gcit.lms.model.BookLoanId;
import com.gcit.lms.model.Borrower;
import com.gcit.lms.model.Branch;
import com.gcit.lms.model.Publisher;

@RestController
@RequestMapping(value = "/", consumes = "application/json", produces = "application/json")
public class Administrator<T>
{
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
	
	@GetMapping(value = "/{collection}", produces = "application/json")
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
	
	@GetMapping(value = "/books/authors/{authId}", produces = "application/json")
	public ResponseEntity< List<Book> > getBooksByAuthId(@PathVariable Long authId)
	{
		List<Book> temp = books.findByAuthorId(authId);
		
		if(temp == null)	return new ResponseEntity< List<Book> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<Book> >(temp, HttpStatus.OK);
	}
	
	@GetMapping(value = "/books/publishers/{pubId}", produces = "application/json")
	public ResponseEntity< List<Book> > getBooksByPubId(@PathVariable Long pubId)
	{
		List<Book> temp = books.findByPublisherId(pubId);
		
		if(temp == null)	return new ResponseEntity< List<Book> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<Book> >(temp, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bookCopies/books/{bookId}", produces = "application/json")
	public ResponseEntity< List<BookCopy> > getBookCopiesByBookId(@PathVariable Long bookId)
	{
		List<BookCopy> temp = bookCopies.findByBookCopyIdBookId(bookId);
		
		if(temp == null)	return new ResponseEntity< List<BookCopy> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<BookCopy> >(temp, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bookCopies/branches/{branchId}", produces = "application/json")
	public ResponseEntity< List<BookCopy> > getBookCopiesByBranchId(@PathVariable Long branchId)
	{
		List<BookCopy> temp = bookCopies.findByBookCopyIdBranchId(branchId);
		
		if(temp == null)	return new ResponseEntity< List<BookCopy> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<BookCopy> >(temp, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bookLoans/books/{bookId}", produces = "application/json")
	public ResponseEntity< List<BookLoan> > getBookLoansByBookId(@PathVariable Long bookId)
	{
		List<BookLoan> temp = bookLoans.findByBookLoanIdBookId(bookId);
		
		if(temp == null)	return new ResponseEntity< List<BookLoan> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<BookLoan> >(temp, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bookLoans/branches/{branchId}", produces = "application/json")
	public ResponseEntity< List<BookLoan> > getBookLoansByBranchId(@PathVariable Long branchId)
	{
		List<BookLoan> temp = bookLoans.findByBookLoanIdBranchId(branchId);
		
		if(temp == null)	return new ResponseEntity< List<BookLoan> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<BookLoan> >(temp, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bookLoans/cardNo/{cardNo}", produces = "application/json")
	public ResponseEntity< List<BookLoan> > getBookLoansByCardNo(@PathVariable Long cardNo)
	{
		List<BookLoan> temp = bookLoans.findByBookLoanIdCardNo(cardNo);
		
		if(temp == null)	return new ResponseEntity< List<BookLoan> >(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity< List<BookLoan> >(temp, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{collection}/{id}", produces = "application/json")
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
	
	@GetMapping(value = "/bookCopies/books/{bookId}/branches/{branchId}", produces = "application/json")
	public ResponseEntity<BookCopy> getBookCopy(@PathVariable Long bookId, @PathVariable Long branchId)
	{
		BookCopy temp = null;
		BookCopyId bookCopyId = new BookCopyId(bookId, branchId);
		
		if( bookCopies.existsById(bookCopyId) )
			temp = bookCopies.findById(bookCopyId).get();
		
		if(temp == null)	return new ResponseEntity<BookCopy>(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity<BookCopy>(temp, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bookLoans/books/{bookId}/branches/{branchId}/cardNo/{cardNo}", produces = "application/json")
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
	 * POST
	 */
	
	@PostMapping(value = "/author", consumes = "application/json")
	public ResponseEntity<Author> addAuthor(@RequestBody Author author)
	{
		Author temp = authors.save(author);
		return new ResponseEntity<Author>(temp, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/book", consumes = "application/json")
	public ResponseEntity<Book> addBook(@RequestBody Book book)
	{
		Book temp = books.save(book);
		return new ResponseEntity<Book>(temp, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/bookCopy", consumes = "application/json")
	public ResponseEntity<BookCopy> addBookCopy(@RequestBody BookCopy bookCopy)
	{
		if( bookCopies.existsById(bookCopy.getBookCopyId()) )
			return new ResponseEntity<BookCopy>(HttpStatus.FORBIDDEN);
		
		BookCopy temp = bookCopies.save(bookCopy);
		return new ResponseEntity<BookCopy>(temp, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/bookLoan", consumes = "application/json")
	public ResponseEntity<BookLoan> addBookLoan(@RequestBody BookLoan bookLoan)
	{
		if( bookLoans.existsById(bookLoan.getBookLoanId()) )
			return new ResponseEntity<BookLoan>(HttpStatus.FORBIDDEN);
		
		BookLoan temp = bookLoans.save(bookLoan);
		return new ResponseEntity<BookLoan>(temp, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/borrower", consumes = "application/json")
	public ResponseEntity<Borrower> addBorrower(@RequestBody Borrower borrower)
	{
		Borrower temp = borrowers.save(borrower);
		return new ResponseEntity<Borrower>(temp, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/branch", consumes = "application/json")
	public ResponseEntity<Branch> addBranch(@RequestBody Branch branch)
	{
		Branch temp = branches.save(branch);
		return new ResponseEntity<Branch>(temp, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/publisher", consumes = "application/json")
	public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher)
	{
		Publisher temp = publishers.save(publisher);
		return new ResponseEntity<Publisher>(temp, HttpStatus.CREATED);
	}
	
	
	
	
	
	/*
	 * PUT
	 */
	
	@PutMapping(value = "/authors", consumes = "application/json")
	public ResponseEntity<Author> updateAuthor(@RequestBody Author author)
	{
		Author temp = null;
		if( authors.existsById(author.getAuthorId()) )
			temp = authors.findById( author.getAuthorId() ).get();
		
		if(temp == null)
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		
		temp.setAuthorName( author.getAuthorName() );
		
		temp = authors.save(temp);
		return new ResponseEntity<Author>(temp, HttpStatus.OK);
	}
	
	@PutMapping(value = "/books", consumes = "application/json")
	public ResponseEntity<Book> updateBook(@RequestBody Book book)
	{
		Book temp = null;
		if( books.existsById(book.getBookId()) )
			temp = books.findById( book.getBookId() ).get();
		
		if(temp == null)
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		
		temp.setTitle( book.getTitle() );
		temp.setAuthorId( book.getAuthorId() );
		temp.setPublisherId( book.getPublisherId() );
		
		temp = books.save(temp);
		return new ResponseEntity<Book>(temp, HttpStatus.OK);
	}
	
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
	
	@PutMapping(value = "/bookLoans", consumes = "application/json")
	public ResponseEntity<BookLoan> updateBookLoan(@RequestBody BookLoan bookLoan)
	{
		BookLoan temp = null;
		if( bookLoans.existsById(bookLoan.getBookLoanId()) )
			temp = bookLoans.findById( bookLoan.getBookLoanId() ).get();
		
		if(temp == null)
			return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND);
		
		temp.setDateOut( bookLoan.getDateOut() );
		temp.setDueDate( bookLoan.getDueDate() );
		
		temp = bookLoans.save(temp);
		return new ResponseEntity<BookLoan>(temp, HttpStatus.OK);
	}
	
	@PutMapping(value = "/borrowers", consumes = "application/json")
	public ResponseEntity<Borrower> updateBorrower(@RequestBody Borrower borrower)
	{
		Borrower temp = null;
		if( borrowers.existsById(borrower.getCardNo()) )
			temp = borrowers.findById( borrower.getCardNo() ).get();
		
		if(temp == null)
			return new ResponseEntity<Borrower>(HttpStatus.NOT_FOUND);
		
		temp.setName( borrower.getName() );
		temp.setAddress( borrower.getAddress() );
		temp.setPhone( borrower.getPhone() );
		
		temp = borrowers.save(temp);
		return new ResponseEntity<Borrower>(temp, HttpStatus.OK);
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
	
	@PutMapping(value = "/publishers", consumes = "application/json")
	public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher publisher)
	{
		Publisher temp = null;
		if( publishers.existsById(publisher.getPublisherId()) )
			temp = publishers.findById( publisher.getPublisherId() ).get();
		
		if(temp == null)
			return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);
		
		temp.setPublisherName( publisher.getPublisherName() );
		temp.setPublisherAddress( publisher.getPublisherAddress() );
		temp.setPublisherPhone( publisher.getPublisherPhone() );
		
		temp = publishers.save(temp);
		return new ResponseEntity<Publisher>(temp, HttpStatus.OK);
	}
	
	
	
	
	
	/*
	 * DELETE
	 */
	
	@DeleteMapping(value = "/{collection}/{id}", produces = "application/json")
	@SuppressWarnings("unchecked")
	public ResponseEntity<T> deleteElement(@PathVariable String collection, @PathVariable Long id)
	{
		T temp = null;
		
		if( collection.equalsIgnoreCase("authors") && authors.existsById(id) )
		{
			temp = (T)authors.findById(id).get();
			authors.deleteById(id);
		}
		else if( collection.equalsIgnoreCase("books") && books.existsById(id) )
		{
			temp = (T)books.findById(id).get();
			books.deleteById(id);
		}
		else if( collection.equalsIgnoreCase("borrowers") && borrowers.existsById(id) )
		{
			temp = (T)borrowers.findById(id).get();
			borrowers.deleteById(id);
		}
		else if( collection.equalsIgnoreCase("branches") && branches.existsById(id) )
		{
			temp = (T)branches.findById(id).get();
			branches.deleteById(id);
		}
		else if( collection.equalsIgnoreCase("publishers") && publishers.existsById(id) )
		{
			temp = (T)publishers.findById(id).get();
			publishers.deleteById(id);
		}
		
		if(temp == null)	return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		else				return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "/bookCopies/books/{bookId}/branches/{branchId}", produces = "application/json")
	public ResponseEntity<BookCopy> deleteBookCopy(@PathVariable Long bookId, @PathVariable Long branchId)
	{
		BookCopy temp = null;
		BookCopyId bookCopyId = new BookCopyId(bookId, branchId);
		
		if( bookCopies.existsById(bookCopyId) )
			temp = bookCopies.findById(bookCopyId).get();
		
		if(temp == null)
			return new ResponseEntity<BookCopy>(HttpStatus.NOT_FOUND);
		
		bookCopies.deleteById(bookCopyId);
		return new ResponseEntity<BookCopy>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "/bookLoans/books/{bookId}/branches/{branchId}/cardNo/{cardNo}", produces = "application/json")
	public ResponseEntity<BookLoan> deleteBookLoan(@PathVariable Long bookId, @PathVariable Long branchId, @PathVariable Long cardNo)
	{
		BookLoan temp = null;
		BookLoanId bookLoanId = new BookLoanId(bookId, branchId, cardNo);
		
		if( bookLoans.existsById(bookLoanId) )
			temp = bookLoans.findById(bookLoanId).get();
		
		if(temp == null)
			return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND);
		
		bookLoans.deleteById(bookLoanId);
		return new ResponseEntity<BookLoan>(HttpStatus.NO_CONTENT);
	}
}