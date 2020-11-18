package com.mcjag.daytoday.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer ";
    private String firstName;
    private String lastName;
	private String email;

	public JwtResponse(String accessToken, String firstName, String lastName, String email) {
		this.token = accessToken;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    public String getLastName() {
        return lastName;
    }

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
