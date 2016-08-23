package io.pivotal.microservices.accounts.db.model;

import java.io.Serializable;

public class ReplyMessage implements Serializable{
	private static final long serialVersionUID = 6590443458021255883L;
	protected boolean isSucc;
	protected String mesage;
	public boolean isSucc() {
		return isSucc;
	}
	public void setSucc(boolean isSucc) {
		this.isSucc = isSucc;
	}
	public String getMesage() {
		return mesage;
	}
	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
	
}
