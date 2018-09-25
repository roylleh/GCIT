package com.gcit.lms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_borrower")
public class Borrower
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long	cardNo;
    private String	name;
    private String	address;
    private String	phone;
    
    @OneToMany(mappedBy = "borrower")
	private List<BookLoan> bookLoans;
    
    public Long getCardNo()
    {
        return cardNo;
    }
    
    public void setCardNo(Long cardNo)
    {
        this.cardNo = cardNo;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}