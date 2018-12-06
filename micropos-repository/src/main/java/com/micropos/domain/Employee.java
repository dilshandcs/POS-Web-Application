package com.micropos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false, unique=true)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id", nullable = false)
	private User user;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_id", nullable = false)
	private Sales sale;

	@Column(name="email")
	private Double email;

	@Column(name="type")
	private Double type;

	@Column(name="total")
	private Double total;

	@Column(name="date" , nullable = false)
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Sales getSale() {
		return sale;
	}

	public void setSale(Sales sale) {
		this.sale = sale;
	}

	public Double getEmail() {
		return email;
	}

	public void setEmail(Double email) {
		this.email = email;
	}

	public Double getType() {
		return type;
	}

	public void setType(Double type) {
		this.type = type;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



}
