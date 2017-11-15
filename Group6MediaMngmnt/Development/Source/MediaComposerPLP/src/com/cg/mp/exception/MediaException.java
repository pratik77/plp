package com.cg.mp.exception;

public class MediaException extends Exception {

	public MediaException() {
	}

	public MediaException(String message) {
		super(message);
	}

	public MediaException(Throwable cause) {
		super(cause);
	}

	public MediaException(String message, Throwable cause) {
		super(message, cause);
	}

	public MediaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
