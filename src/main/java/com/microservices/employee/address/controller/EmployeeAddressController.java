package com.microservices.employee.address.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.employee.address.model.EmployeeAddress;
import com.microservices.employee.address.service.EmployeeAddressServiceIF;
import com.microservices.result.Result;

@RestController
@RequestMapping(path = "/employeeaddress/v1")
public class EmployeeAddressController {

	
	
	@Autowired
	private EmployeeAddressServiceIF employeeAddressServiceIF;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/employeeaddresses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getEmployeeAddresses() {

		HttpStatus statusCode = null;
		Result result = new Result();
		List<EmployeeAddress> employeeAddressList = null;
		try {
			employeeAddressList = employeeAddressServiceIF.getEmployeeAddresses();
			if (employeeAddressList.isEmpty()) {
//				result.setMessage(ApplicationMessages.CUSTOMERS_LIST_EMPTY);
//				result.setStatus(ApplicationConstants.SUCCESS);
				result.setObject(employeeAddressList);
			} else {
				result.setObject(employeeAddressList);
				statusCode = HttpStatus.OK;
//				result.setStatus(ApplicationConstants.SUCCESS);
//				result.setMessage(ApplicationMessages.CUSTOMERS_LIST_NOT_EMPTY);
			}

		} catch (Exception e) {
			//logger.error(ApplicationMessages.CUSTOMER_METHED_FIND_ALL);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employeeAddressList);
			//result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// EmployeeException(ApplicationConstants.CUSTOMER_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getEmployeeAddress(@PathVariable("employeeId") String employeeId) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object employeeAddress = null;
		try {
			employeeAddress = employeeAddressServiceIF.getEmployeeAddress(Long.parseLong(employeeId));
			if (employeeAddress == null) {
				result.setObject(employeeAddress);
				statusCode = HttpStatus.NO_CONTENT;
				//result.setStatus(ApplicationConstants.FAILED);
				//result.setMessage(ApplicationMessages.CUSTOMER_DOES_NOT_EXIST);
			} else {
				result.setObject(employeeAddress);
				statusCode = HttpStatus.OK;
				//result.setStatus(ApplicationConstants.SUCCESS);
				//result.setMessage(ApplicationMessages.CUSTOMER_EXIST);
			}

		} catch (Exception e) {
			//logger.error(ApplicationMessages.CUSTOMER_METHED_FIND_ONE);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employeeAddress);
			//result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// EmployeeAddressException(ApplicationConstants.CUSTOMER_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	public EmployeeAddressServiceIF getEmployeeAddressServiceIF() {
		return employeeAddressServiceIF;
	}

	public void setEmployeeAddressServiceIF(EmployeeAddressServiceIF employeeAddressServiceIF) {
		this.employeeAddressServiceIF = employeeAddressServiceIF;
	}

	
}
