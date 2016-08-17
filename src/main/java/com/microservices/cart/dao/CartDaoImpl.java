package com.microservices.cart.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.cart.exception.CartException;
import com.microservices.cart.model.Cart;

@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CartDaoImpl implements CartDaoIF {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cart> getCarts() throws CartException {
		Session session = null;
		List<Cart> cartList = null;
		try {
			session = sessionFactory.getCurrentSession();
			cartList = session.createQuery("from Cart").list();
			//session.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
		}
		return cartList;
	}

	@Override
	public Object getCart(Long cartId) throws CartException {
		Session session = null;
		Object employee = null;
		try {
			session = sessionFactory.getCurrentSession();
			employee = session.get(Cart.class,cartId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {

		}
		return employee;
	}

	@Override
	public Object saveCart(Cart cart) throws CartException {
		Session session = null;
		Long id = 0L;
		try {
			session = sessionFactory.getCurrentSession();
			id = (Long)session.save(cart);
			return getCart(id);
		} catch (Exception ex) {
			throw ex;
		} finally {
		}
		
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}
