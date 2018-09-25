package com.gcit.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableEurekaClient
@SpringBootApplication
public class LMSBorrower
{
	public static void main(String[] args)
	{
		SpringApplication.run(LMSBorrower.class, args);
	}
}