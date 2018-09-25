package com.gcit.lms.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book_loans")
public class BookLoan
{
	@EmbeddedId
	private BookLoanId	bookLoanId;
	private String		dateOut;
	private String		dueDate;
	
	@ManyToOne
    @JoinColumn(name = "bookId", insertable = false, updatable = false)
    private Book book;
	
	@ManyToOne
    @JoinColumn(name = "branchId", insertable = false, updatable = false)
    private Branch branch;
	
	@ManyToOne
    @JoinColumn(name = "cardNo", insertable = false, updatable = false)
    private Borrower borrower;
	
	public BookLoanId getBookLoanId()
	{
		return bookLoanId;
	}
	
	public void setBookLoanId(BookLoanId bookLoanId)
	{
		this.bookLoanId = bookLoanId;
	}
	
	public String getDateOut()
	{
		return dateOut;
	}
	
	public void setDateOut(String dateOut)
	{
		this.dateOut = dateOut;
	}
	
	public String getDueDate()
	{
		return dueDate;
	}
	
	public void setDueDate(String dueDate)
	{
		this.dueDate = dueDate;
	}
	
	public Book getBook()
	{
		return book;
	}
	
	public void setBook(Book book)
	{
		this.book = book;
	}
	
	public Branch getBranch()
	{
		return branch;
	}
	
	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}
	
	public Borrower getBorrower()
	{
		return borrower;
	}
	
	public void setBorrower(Borrower borrower)
	{
		this.borrower = borrower;
	}
}