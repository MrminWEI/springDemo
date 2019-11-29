package com.example.demo.jwt;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

	private final String token;
	private final String msg;

	public JwtAuthenticationResponse(String token, String msg) {
		this.token = token;
		this.msg = msg;
	}



	public String getToken() {
		return token;
	}

	public String getMsg() {
		return msg;
	}
}
