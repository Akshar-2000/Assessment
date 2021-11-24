package com.tavant.Spring_parallel;

import org.springframework.stereotype.Component;

@Component
public class Transaction {
	private Integer Id;
	private String paidByName;
	private String paidToName;
	private Integer Amount;
	
	public Transaction(String paidByName, String paidToName, Integer amount) {
		super();
		this.paidByName = paidByName;
		this.paidToName = paidToName;
		Amount = amount;
	}
	public Transaction() {
	}
	@Override
	public String toString() {
		return "Transaction [Id=" + Id + ", paidByName=" + paidByName + ", paidToName=" + paidToName + ", Amount="
				+ Amount + "]";
	}
	public Transaction(Integer id, String paidByName, String paidToName, Integer amount) {
		super();
		Id = id;
		this.paidByName = paidByName;
		this.paidToName = paidToName;
		Amount = amount;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getPaidByName() {
		return paidByName;
	}
	public void setPaidByName(String paidByName) {
		this.paidByName = paidByName;
	}
	public String getPaidToName() {
		return paidToName;
	}
	public void setPaidToName(String paidToName) {
		this.paidToName = paidToName;
	}
	public Integer getAmount() {
		return Amount;
	}
	public void setAmount(Integer amount) {
		Amount = amount;
	}
}
