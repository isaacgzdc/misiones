package com.example.misiones.exception;

public class MissionException extends Exception {

	private static final long serialVersionUID = 8684181913049713520L;

	public MissionException(Exception e) {
		super(e);
	}
	
	public MissionException(String msg) {
		super(msg);
	}
	
	public MissionException(String msg, Exception e) {
		super(msg, e);
	}
}
