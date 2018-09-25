package com.gcit.lms.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BookCopyId implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long bookId;
	private Long branchId;
	
	public BookCopyId() {}
	public BookCopyId(Long bookId, Long branchId)
	{
		this.bookId = bookId;
		this.branchId = branchId;
	}
	
	public Long getBookId()
	{
		return bookId;
	}
	
	public void setBookId(Long bookId)
	{
		this.bookId = bookId;
	}
	
	public Long getBranchId()
	{
		return branchId;
	}
	
	public void setBranchId(Long branchId)
	{
		this.branchId = branchId;
	}
}