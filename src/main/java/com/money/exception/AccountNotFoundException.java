package com.money.exception;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Long accountId;

	public AccountNotFoundException(Long accountId) {
		this.accountId = accountId;
	}

	@Override
	public String getMessage() {
		return "Could not find an account with Id " + accountId;
	}

}
