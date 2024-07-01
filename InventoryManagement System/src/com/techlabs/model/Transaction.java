package com.techlabs.model;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	
	private static final long serialVersionUID=1L;
	private String transactionid;
	private String productId;
	private String type;
	private int quantity;
	private Date date;
	public Transaction(String transactionid, String productId, String type, int quantity, Date date) {
		super();
		this.transactionid = transactionid;
		this.productId = productId;
		this.type = type;
		this.quantity = quantity;
		this.date = date;
	}
	public Transaction(String id, String productId1, String type2, int quantity1, String date2) {
		// TODO Auto-generated constructor stub
	}
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Transaction [transactionid=" + transactionid + ", productId=" + productId + ", type=" + type
				+ ", quantity=" + quantity + ", date=" + date + "]";
	}
	
	
}
