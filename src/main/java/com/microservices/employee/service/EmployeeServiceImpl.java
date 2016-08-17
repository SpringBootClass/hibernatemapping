package com.microservices.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.employee.dao.EmployeeDaoIF;
import com.microservices.employee.exception.EmployeeException;
import com.microservices.employee.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeServiceIF {
	@Autowired
	private EmployeeDaoIF employeeDaoIF;
	
	@Override
	public List<Employee> getEmployees() throws EmployeeException {
		return employeeDaoIF.getEmployees();
	}

	@Override
	public Object getEmployee(Long employeeId) throws EmployeeException {
		return employeeDaoIF.getEmployee(employeeId);
	}

	public EmployeeDaoIF getEmployeeDaoIF() {
		return employeeDaoIF;
	}

	public void setEmployeeDaoIF(EmployeeDaoIF employeeDaoIF) {
		this.employeeDaoIF = employeeDaoIF;
	}

	

}
