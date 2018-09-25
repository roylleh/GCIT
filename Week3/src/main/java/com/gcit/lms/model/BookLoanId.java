package com.gcit.lms.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BookLoanId implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long bookId;
	private Long branchId;
	private Long cardNo;
	
	public BookLoanId() {}
	public BookLoanId(Long bookId, Long branchId, Long cardNo)
	{
		this.bookId = bookId;
		this.branchId = branchId;
		this.cardNo = cardNo;
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
	
	public Long getCardNo()
	{
		return cardNo;
	}
	
	public void setCardNo(Long cardNo)
	{
		this.cardNo = cardNo;
	}
}