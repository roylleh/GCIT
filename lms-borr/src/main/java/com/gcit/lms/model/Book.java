package com.gcit.lms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long	bookId;
    private String	title;
    private Long	authorId;
    private Long	publisherId;
    
    @ManyToOne
    @JoinColumn(name = "authorId", insertable = false, updatable = false)
    private Author author;
    
    @ManyToOne
    @JoinColumn(name = "publisherId", insertable = false, updatable = false)
    private Publisher publisher;
    
    @OneToMany(mappedBy = "book")
	private List<BookCopy> bookCopies;
    
    @OneToMany(mappedBy = "book")
	private List<BookLoan> bookLoans;
    
    public Long getBookId()
    {
        return bookId;
    }
    
    public void setBookId(Long bookId)
    {
        this.bookId = bookId;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public Long getAuthorId()
    {
        return authorId;
    }
    
    public void setAuthorId(Long authorId)
    {
        this.authorId = authorId;
    }
    
    public Long getPublisherId()
    {
        return publisherId;
    }
    
    public void setPublisherId(Long publisherId)
    {
        this.publisherId = publisherId;
    }
    
	public Author getAuthor()
	{
		return author;
	}
	
	public void setAuthor(Author author)
	{
		this.author = author;
	}
	
	public Publisher getPublisher()
	{
		return publisher;
	}
	
	public void setPublisher(Publisher publisher)
	{
		this.publisher = publisher;
	}
}