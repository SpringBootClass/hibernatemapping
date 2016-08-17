package com.microservices.cart.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservices.item.model.Item;

@Entity
@Table(name="carts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id")
	private long id;
	
	@Column(name="total")
	private double total;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade={CascadeType.ALL})
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="cart_id", referencedColumnName="cart_id")
	private Set<Item> items;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	

}