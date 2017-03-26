package io.pivotal.microservices.db.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class AccountModel implements Serializable{

	private static final long serialVersionUID = -3649188071950755323L;
	protected Long id;
	protected String number;
	protected String name;
	protected BigDecimal balance;

	
	protected AccountModel() {
		balance = BigDecimal.ZERO;
	}

	public AccountModel(Long id,String number, String owner) {
		this.id = id;
		this.number = number;
		this.name = owner;
		balance = BigDecimal.ZERO;
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	protected void setNumber(String accountNumber) {
		this.number = accountNumber;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public void withdraw(BigDecimal amount) {
		balance.subtract(amount);
	}

	public void deposit(BigDecimal amount) {
		balance.add(amount);
	}

	@Override
	public String toString() {
		return number + " [" + name + "]: $" + balance;
	}

}
