package com.ers.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ers.dao.EmployeeDAO;
import com.ers.model.Employee;
import com.ers.service.RegisterService;
import com.ers.service.LoginService;

public class RegisterController {

	final static Logger loggy = Logger.getLogger(FinancialManagerController.class);
	static RegisterService regEmp = new RegisterService(new EmployeeDAO());
	
	public static String RegisterEmp(HttpServletRequest req) {
		
		System.out.println("in RegisterEmp controller 2");
		
		
		/*
		 * if(!req.getMethod().equals("POST")) { return "html/Login.html"; }
		 */
		// regEmp.register(null, null, null, null, 0, null)
		

		try {
			//int roleid = 2;       //Integer.parseInt(req.getParameter("type"));
			
			System.out.println("Type."+req.getParameter("type")+".");
			int roleid = Integer.parseInt(req.getParameter("type"));
			String passwd = generatePassword();
			String recepientEmail = req.getParameter("email");

			System.out.println("Type." + roleid + ".");
			System.out.println("Password." + passwd + ".");
			System.out.println("Email." + recepientEmail + ".");

			regEmp.register(req.getParameter("Username"), req.getParameter("email"), req.getParameter("firstname"),
					req.getParameter("lastname"), roleid, passwd);
			sendEmail(recepientEmail, passwd);

		} catch (Exception e) {
			loggy.error(e);
		}
		return "html/RegisterSuccessful.html";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void sendEmail(String recepientEmail, String passwd) {
		System.out.println("Inside Email");
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "ers.register@gmail.com";//
		final String password = "1982@Cherpu";
		
		System.out.println("At this point after getting properties Send Email");
		
		try {
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
               
			System.out.println("At this point after password authentication properties Send Email");
			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("ers.register@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepientEmail, false));
			String password1 = "password";
			msg.setSubject("ERS Password");
			msg.setText("Your Password for ERS Account  : " + passwd);
			
			System.out.println("Before Send Email");
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Message sent.");
		} catch (MessagingException e) {
			System.out.println("Error caught: " + e);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////
	public static String generatePassword() {

		
		int total = 1;
		String randomPassword = "";

		
		int length = 8;

		// Create an array of random passwords (Strings)
		String[] randomPasswords = new String[total];

		// Randomly generate passwords total number of times
		for (int i = 0; i < total; i++) {
			// Create a variable to store the random password
			// String randomPassword = "";
			// Randomly generate a character for the password length number of times
			for (int j = 0; j < length; j++) {
				// Add a random lowercase or UPPERCASE character to our randomPassword String
				randomPassword += randomCharacter();
			}
			// Add the random password to your array
			// randomPasswords[i] = randomPassword;
		}

		// Print out the random passwords array
		/* printArray(randomPasswords); */
		// System.out.println(randomPassword);
		return randomPassword;

	}

	// Create a function that prints out an array that is passed in as a parameter
	/*
	 * public static void printArray(String[] arr) { for(int i = 0; i < arr.length;
	 * i++) { System.out.println(arr[i]); } }
	 */

	// Create a function that randomly generates a letter (lowercase or uppercase)
	// or number (0-9) using ASCII
	// '0' - '9' => 48-57 in ASCII
	// 'A' - 'Z' => 65-90 in ASCII
	// 'a' - 'z' => 97-122 in ASCII
	public static char randomCharacter() {
		// We multiply Math.random() by 62 because there are 26 lowercase letters, 26
		// uppercase letters, and 10 numbers, and 26 + 26 + 10 = 62
		// This random number has values between 0 (inclusive) and 62 (exclusive)
		int rand = (int) (Math.random() * 62);

		// 0-61 inclusive = all possible values of rand
		// 0-9 inclusive = 10 possible numbers/digits
		// 10-35 inclusive = 26 possible uppercase letters
		// 36-61 inclusive = 26 possible lowercase letters
		// If rand is between 0 (inclusive) and 9 (inclusive), we can say it's a
		// number/digit
		// If rand is between 10 (inclusive) and 35 (inclusive), we can say it's an
		// uppercase letter
		// If rand is between 36 (inclusive) and 61 (inclusive), we can say it's a
		// lowercase letter
		if (rand <= 9) {
			// Number (48-57 in ASCII)
			// To convert from 0-9 to 48-57, we can add 48 to rand because 48-0 = 48
			int number = rand + 48;
			// This way, rand = 0 => number = 48 => '0'
			// Remember to cast our int value to a char!
			return (char) (number);
		} else if (rand <= 35) {
			// Uppercase letter (65-90 in ASCII)
			// To convert from 10-35 to 65-90, we can add 55 to rand because 65-10 = 55
			int uppercase = rand + 55;
			// This way, rand = 10 => uppercase = 65 => 'A'
			// Remember to cast our int value to a char!
			return (char) (uppercase);
		} else {
			// Lowercase letter (97-122 in ASCII)
			// To convert from 36-61 to 97-122, we can add 61 to rand because 97-36 = 61
			int lowercase = rand + 61;
			// This way, rand = 36 => lowercase = 97 => 'a'
			// Remember to cast our int value to a char!
			return (char) (lowercase);
		}
	}

}
