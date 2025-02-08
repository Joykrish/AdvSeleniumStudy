package com.ui.pojos;

public class Customer {
	
	private String fName;
	private String lName;
	private String postNumber;
	

	public Customer(String fName, String lName,String postNumber) {
		this.fName=fName;
		this.lName=fName;
		this.postNumber=fName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}
	@Override
	public String toString() {
		return "Customer [fName=" + fName + ", lName=" + lName + ", postNumber=" + postNumber + "]";
	}

}
