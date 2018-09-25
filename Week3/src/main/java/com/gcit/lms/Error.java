package com.gcit.lms;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Error implements ErrorController
{
	private static final String ERROR_PATH = "/error";

	@Override
	public String getErrorPath()
	{
		return ERROR_PATH;
	}
	
	@RequestMapping(ERROR_PATH)
	public String getError()
	{
		return "Something went wrong. A team of highly trained unpaid interns has been dispatched to resolve the issue.\n\n400: Invalid request body.\n403: Element already exists in collection.\n404: Element does not exist in collection.\n405: Invalid endpoint.\n500: ¯\\_(ツ)_/¯";
	}
}