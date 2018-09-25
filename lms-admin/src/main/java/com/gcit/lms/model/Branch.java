package com.gcit.lms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_library_branch")
public class Branch
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long	branchId;
    private String	branchName;
    private String	branchAddress;
    
    @OneToMany(mappedBy = "branch")
	private List<BookCopy> bookCopies;
    
    @OneToMany(mappedBy = "branch")
	private List<BookLoan> bookLoans;
    
    public Long getBranchId()
    {
        return branchId;
    }
    
    public void setBranchId(Long branchId)
    {
        this.branchId = branchId;
    }
    
    public String getBranchName()
    {
        return branchName;
    }
    
    public void setBranchName(String branchName)
    {
        this.branchName = branchName;
    }
    
    public String getBranchAddress()
    {
        return branchAddress;
    }
    
    public void setBranchAddress(String branchAddress)
    {
        this.branchAddress = branchAddress;
    }
}