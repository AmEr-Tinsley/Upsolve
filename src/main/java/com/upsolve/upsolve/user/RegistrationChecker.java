package com.upsolve.upsolve.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.upsolve.upsolve.validation.FieldMatch;
import com.upsolve.upsolve.validation.ValidEmail;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class RegistrationChecker {

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	
	private String role;
	
	public RegistrationChecker() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public RegistrationChecker(
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String userName,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String password,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String matchingPassword,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String email, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.email = email;
		this.role = role;
	}

	@Override
	public String toString() {
		return "RegistrationChecker [userName=" + userName + ", password=" + password + ", matchingPassword="
				+ matchingPassword + ", email=" + email + ", role=" + role + "]";
	}

	

}
