package com.gcit.lms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_publisher")
public class Publisher
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long	publisherId;
    private String	publisherName;
    private String	publisherAddress;
    private String	publisherPhone;
    
    @OneToMany(mappedBy = "publisher")
	private List<Book> books;
    
    public Long getPublisherId()
    {
        return publisherId;
    }
    
    public void setPublisherId(Long publisherId)
    {
        this.publisherId = publisherId;
    }
    
    public String getPublisherName()
    {
        return publisherName;
    }
    
    public void setPublisherName(String publisherName)
    {
        this.publisherName = publisherName;
    }
    
    public String getPublisherAddress()
    {
        return publisherAddress;
    }
    
    public void setPublisherAddress(String publisherAddress)
    {
        this.publisherAddress = publisherAddress;
    }
    
    public String getPublisherPhone()
    {
        return publisherPhone;
    }
    
    public void setPublisherPhone(String publisherPhone)
    {
        this.publisherPhone = publisherPhone;
    }
}