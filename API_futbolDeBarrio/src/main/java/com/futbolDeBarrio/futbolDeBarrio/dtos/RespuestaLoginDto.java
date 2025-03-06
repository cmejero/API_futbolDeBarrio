package com.futbolDeBarrio.futbolDeBarrio.dtos;

public class RespuestaLoginDto {
	  private String token;

	    // Constructor
	    public RespuestaLoginDto(String token) {
	        this.token = token;
	    }

	    // Getter y Setter
	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }
}
