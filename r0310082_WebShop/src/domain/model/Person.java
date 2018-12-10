package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userid;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;

	public Person(String userid, String email, String password, String firstName, String lastName) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	public Person() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new IllegalArgumentException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email;
	}

	
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		String passwordHash = hashPassword(password);
		return passwordHash.equals(getPassword());
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object object) throws DomainException {
		boolean isSamePerson = false;
		if (object instanceof Person) {
			Person thatPerson = (Person) object;
			isSamePerson = this.getFirstName().equals(thatPerson.getFirstName())
					&& this.getLastName().equals(thatPerson.getLastName())
					&& this.getEmail().equals(thatPerson.getEmail());
		}
		return isSamePerson;
	}

	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail()
				+ "\n password: " + getPassword();
	}

	// HASHING
	public void setPasswordHashed(String password) {
		setPassword(hashPassword(password));
	}

	private String hashPassword(String password){
		MessageDigest crypt;
		byte[] passwordBytes;

		try {
			crypt = MessageDigest.getInstance("SHA-512");

		}
		catch (NoSuchAlgorithmException exc) {
			throw new DomainException(exc.getMessage(), exc);
		}
		try {
			passwordBytes = password.getBytes("UTF-8");
		}
		catch (UnsupportedEncodingException exc) {
			throw new DomainException(exc.getMessage(), exc);
		}
		crypt.reset();
		crypt.update(passwordBytes);

		byte[] digest = crypt.digest();
		BigInteger digestAsBitInteger = new BigInteger(1, digest);

		return digestAsBitInteger.toString(16);
	}

}
