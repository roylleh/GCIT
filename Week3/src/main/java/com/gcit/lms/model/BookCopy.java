package com.gcit.lms.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book_copies")
public class BookCopy
{
	@EmbeddedId
	private BookCopyId	bookCopyId;
	private Long		noOfCopies;
	
	@ManyToOne
    @JoinColumn(name = "bookId", insertable = false, updatable = false)
    private Book book;
	
	@ManyToOne
    @JoinColumn(name = "branchId", insertable = false, updatable = false)
    private Branch branch;
	
	public BookCopyId getBookCopyId()
	{
		return bookCopyId;
	}
	
	public void setBookCopyId(BookCopyId bookCopyId)
	{
		this.bookCopyId = bookCopyId;
	}
	
	public Long getNoOfCopies()
	{
		return noOfCopies;
	}
	
	public void setNoOfCopies(Long noOfCopies)
	{
		this.noOfCopies = noOfCopies;
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
}