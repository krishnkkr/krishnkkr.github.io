package com.highradius.internship;

public class User {
	
	 String Order_ID,Customer_Name,Customer_ID,Order_Amount,Approval_Status,Approved_By,Notes,Order_Date;

	
	
	
	
	String Level="";
	
	public User() {}
	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public User(String order_ID, String customer_Name, String customer_ID, String order_Amount, String approval_Status,
			String approved_By, String notes, String order_Date) {
		super();
		this.Order_ID = order_ID;
		this.Customer_Name = customer_Name;
		this.Customer_ID = customer_ID;
		this.Order_Amount = order_Amount;
		this.Approval_Status = approval_Status;
		this.Approved_By = approved_By;
		this.Notes = notes;
		this.Order_Date = order_Date;
	}

	public String getOrder_ID() {
		return Order_ID;
	}

	public void setOrder_ID(String order_ID) {
		Order_ID = order_ID;
	}

	public String getCustomer_Name() {
		return Customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}

	public String getCustomer_ID() {
		return Customer_ID;
	}

	public void setCustomer_ID(String customer_ID) {
		Customer_ID = customer_ID;
	}

	public String getOrder_Amount() {
		return Order_Amount;
	}

	public void setOrder_Amount(String order_Amount) {
		Order_Amount = order_Amount;
	}

	public String getApproval_Status() {
		return Approval_Status;
	}

	public void setApproval_Status(String approval_Status) {
		Approval_Status = approval_Status;
	}

	public String getApproved_By() {
		return Approved_By;
	}

	public void setApproved_By(String approved_By) {
		Approved_By = approved_By;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}

	public String getOrder_Date() {
		return Order_Date;
	}

	public void setOrder_Date(String order_Date) {
		Order_Date = order_Date;
	}
	
}
