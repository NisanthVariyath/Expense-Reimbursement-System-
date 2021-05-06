package com.ers.model;

public class Employee implements Comparable {
	
	private int ers_user_id;
	private String ers_username;
	private PHash ers_password;
	private String user_firstname;
	private  String user_lastname;
	private String  user_email;
	private int user_role_id;
	
	public Employee() {
			
	}
	
	public Employee(int ers_user_id, String ers_username, PHash ers_password, String user_firstname,
			String user_lastname, String user_email, int user_role_id2) {
		super();
		this.ers_user_id = ers_user_id;
		this.ers_username = ers_username;
		this.ers_password = ers_password;
		this.user_firstname = user_firstname;
		this.user_lastname = user_lastname;
		this.user_email = user_email;
		this.user_role_id = user_role_id2;
	}

	public int getErs_user_id() {
		return ers_user_id;
	}

	public void setErs_user_id(int ers_user_id) {
		this.ers_user_id = ers_user_id;
	}

	public String getErs_username() {
		return ers_username;
	}

	public void setErs_username(String ers_username) {
		this.ers_username = ers_username;
	}

	public PHash getErs_password() {
		return ers_password;
	}
// SET ERS_PASSWORD TO ENCRYPT USING PHASH
	public void setErs_password(String s) {
		if ((this.ers_password) == null) {
			this.ers_password = new PHash(s);
		} else {
		    this.ers_password.setPassword(s);
		}
	}

	public String getUser_firstname() {
		return user_firstname;
	}

	public void setUser_firstname(String user_firstname) {
		this.user_firstname = user_firstname;
	}

	public String getUser_lastname() {
		return user_lastname;
	}

	public void setUser_lastname(String user_lastname) {
		this.user_lastname = user_lastname;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	@Override
	public String toString() {
		return "Employee [ers_user_id=" + ers_user_id + ", ers_username=" + ers_username + ", ers_password="
				+ ers_password + ", user_firstname=" + user_firstname + ", user_lastname=" + user_lastname
				+ ", user_email=" + user_email + ", user_role_id=" + user_role_id + "]";
	}
	
/*check password  , using The checkPassword function takes an unencrypted string and 
    returns true if that string matches the encrypted  password. */ 
	public boolean checkPassword(String s) {
		return this.ers_password.checkPassword(s);
	}

	
	@Override
	public int compareTo(Object other) {
		return Long.compare(this.ers_user_id, ((Employee)other).getUser_role_id());
	}
	
	
}
