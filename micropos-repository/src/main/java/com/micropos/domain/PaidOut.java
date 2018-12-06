package com.micropos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paid_out")
public class PaidOut implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false, unique=true)
	private Integer id;

	@Column(name="description")
	private String description;

	@Column(name="amount")
	private Double amount;

	@Column(name="date")
	private Date date;

	@Column(name="paidout_item")
	private Integer paidoutItem;



	public Integer getPaidoutItem() {
		return paidoutItem;
	}

	public void setPaidoutItem(Integer paidoutItem) {
		this.paidoutItem = paidoutItem;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


}
