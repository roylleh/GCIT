package com.gcit.lms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_author")
public class Author
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	authorId;
	private String	authorName;
	
	@OneToMany(mappedBy = "author")
	private List<Book> books;
	
	public Long getAuthorId()
	{
		return authorId;
	}
	
	public void setAuthorId(Long authorId)
	{
		this.authorId = authorId;
	}
	
	public String getAuthorName()
	{
		return authorName;
	}
	
	public void setAuthorName(String authorName)
	{
		this.authorName = authorName;
	}
}