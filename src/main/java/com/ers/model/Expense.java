package com.ers.model;

import java.io.File;
import java.sql.Blob;
import java.sql.Timestamp;

public class Expense implements Comparable {
	
	private int reimb_id;
	private String firstname;
	private  String lastname;
	public Expense(int reimb_id, String firstname, String lastname, Double reimb_amount, Timestamp reimb_submitted,
			Timestamp reimb_resolved, String reimb_description, Blob reimb_recipt, int reimb_author, int reimb_resolver,
			int reimb_status_id, int reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_recipt = reimb_recipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}



	private Double reimb_amount;
	private Timestamp reimb_submitted;  // time stamp
	private Timestamp reimb_resolved;   // time stamp
	private String reimb_description;
	private Blob  reimb_recipt;    // type blob or BYTEA 
	private int reimb_author;
	private int reimb_resolver;
	private int reimb_status_id;  // need to fetch from database  ERS_REIMBURSEMENT_STATUS
	private int reimb_type_id; //need to fetch from database    ERS_REIMBURSEMENT_TYPE 
	

	
	public Expense() {
		super();
	
	}

	public Expense(int reimb_id, Double reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved,
			String reimb_description, Blob reimb_recipt, int reimb_author, int reimb_resolver, int reimb_status_id,
			int reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_recipt = reimb_recipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public Double getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(Double reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public Timestamp getReimb_submitted() {
		return reimb_submitted;
	}

	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public Timestamp getReimb_resolved() {
		return reimb_resolved;
	}

	public void setReimb_resolved(Timestamp reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public Blob getReimb_recipt() {
		return reimb_recipt;
	}

	public void setReimb_recipt(Blob reimb_recipt) {
		this.reimb_recipt = reimb_recipt;
	}

	public int getReimb_author() {
		return reimb_author;
	}

	public void setReimb_author(int reimb_author) {
		this.reimb_author = reimb_author;
	}

	public int getReimb_resolver() {
		return reimb_resolver;
	}

	public void setReimb_resolver(int reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}

	public int getReimb_status_id() {
		return reimb_status_id;
	}

	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}

	public int getReimb_type_id() {
		return reimb_type_id;
	}

	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}



	
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Expense [reimb_id=" + reimb_id + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", reimb_amount=" + reimb_amount + ", reimb_submitted=" + reimb_submitted + ", reimb_resolved="
				+ reimb_resolved + ", reimb_description=" + reimb_description + ", reimb_recipt=" + reimb_recipt
				+ ", reimb_author=" + reimb_author + ", reimb_resolver=" + reimb_resolver + ", reimb_status_id="
				+ reimb_status_id + ", reimb_type_id=" + reimb_type_id + "]";
	}

	@Override
	public int compareTo(Object other) {
		return Long.compare(this.reimb_id, ((Expense)other).getReimb_id());
	}
	
	

}
