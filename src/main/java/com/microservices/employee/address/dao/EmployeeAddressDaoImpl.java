package com.microservices.employee.address.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.employee.address.exception.EmployeeAddressException;
import com.microservices.employee.address.model.EmployeeAddress;

@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class EmployeeAddressDaoImpl implements EmployeeAddressDaoIF {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeAddress> getEmployeeAddresses() throws EmployeeAddressException {
		Session session = null;
		List<EmployeeAddress> employeeAddressesList = null;

		try {
			session = sessionFactory.getCurrentSession();
			employeeAddressesList = session.createQuery("from EmployeeAddress").list();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			
		}
		return employeeAddressesList;
	}

	@Override
	public Object getEmployeeAddress(Long employeeId) throws EmployeeAddressException {
		Session session = null;
		Object employeeAddress = null;

		try {
			session = sessionFactory.getCurrentSession();
			employeeAddress = session.get(EmployeeAddress.class,employeeId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {

		}
		return employeeAddress;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
}
