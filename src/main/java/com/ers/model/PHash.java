package com.ers.model;

import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PHash {
	private String pwHash;
	
	public PHash() {
		super();
		this.pwHash = new String();
	}
	
	/** Only for use when loading values from persistent storage */
	public PHash(String s) {
		this();
		this.setHash(s); 
	}
	
	/** Only for saving values to persistent storage. */
	public String getHash() {
		return pwHash;
	}
	
	/** Only for use when loading values from persistent storage. */
	private void setHash(String s) {
		this.pwHash = s;
		//return ;
	}
	
	/** For security reasons, there is no getPwHash method.
	 * The checkPassword function takes an unencrypted string
	 * and returns true if that string matches the encrypted
	 * password.  Otherwise, this method returns false. */
	public boolean checkPassword(String s) {
		return (passwordEncoder().matches(s, this.pwHash));
	}
	
	/** Function setPassword takes a raw string, generates a
	 * random salt, and encodes the string into pwHash.  This value
	 * should never be accessible by the user. */
	public void setPassword(String s) {
		String bcrypt = null;
		
		bcrypt = passwordEncoder().encode(s);
		setHash(bcrypt); 
	}
	
	/** Function passwordEncoder returns a new BCryptPasswordEncoder,
	 * which contains methods useful for encrypting our password. */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}	@Override
	
	/** Hashing our pwHash field should obscure the value enough to 
	 * ensure that this required functionality does not overly compromise
	 * the security of our pwHash field. */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pwHash == null) ? 0 : pwHash.hashCode());
		return result;
	}

	/** Because the pwHash values are never disclosed to the user, this code
	 * should be safe. */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PHash other = (PHash) obj;
		if (pwHash == null) {
			if (other.pwHash != null)
				return false;
		} else if (!pwHash.equals(other.pwHash))
			return false;
		return true;
	}

	/** Function toString returns a snarky string letting the end user
	 * know not to attempt to bypass our security. */
	@Override
	public String toString() {
		return "PHash [salt, pwHash= Nope, you don't ever get to see the salt or hash values.]";
	}
}
