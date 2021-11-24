package com.tavant.waleed.training;
import org.springframework.stereotype.Component;

public class Accounts {
	private Integer Id;
	private String accName;
	private String accPwd;
	private String dateOfCreation;
	private Integer amount;
	public Integer getId() {
		return Id;
	}
	
	public Accounts(String accName, String accPwd, String dateOfCreation, Integer amount) {
		super();
		this.accName = accName;
		this.accPwd = accPwd;
		this.dateOfCreation = dateOfCreation;
		this.amount = amount;
	}

	public Accounts(String accName, String accPwd) {
		super();
		this.accName = accName;
		this.accPwd = accPwd;
	}

	public Accounts(){}
	@Override
	public String toString() {
		return "Accounts [Id=" + Id + ", accName=" + accName + ", accPwd=" + accPwd + ", dateOfCreation="
				+ dateOfCreation + ", amount=" + amount + "]";
	}
	public Accounts(Integer id, String accName, String accPwd, String dateOfCreation, Integer amount) {
		super();
		Id = id;
		this.accName = accName;
		this.accPwd = accPwd;
		this.dateOfCreation = dateOfCreation;
		this.amount = amount;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getAccPwd() {
		return accPwd;
	}
	public void setAccPwd(String accPwd) {
		this.accPwd = accPwd;
	}
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
