package com.example.demo.jwt;


import com.example.demo.grcloud.page.BaseResponse;

public class TokenErrorResponse extends BaseResponse {
	
	  public TokenErrorResponse(String message) {
	        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
	    }

}
