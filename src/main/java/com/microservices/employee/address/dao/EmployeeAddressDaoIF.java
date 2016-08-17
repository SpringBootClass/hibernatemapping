package com.microservices.employee.address.dao;

import java.util.List;

import com.microservices.employee.address.exception.EmployeeAddressException;
import com.microservices.employee.address.model.EmployeeAddress;

public interface EmployeeAddressDaoIF {
	public List<EmployeeAddress> getEmployeeAddresses() throws EmployeeAddressException;
	public Object getEmployeeAddress(Long employeeId) throws EmployeeAddressException;
}
