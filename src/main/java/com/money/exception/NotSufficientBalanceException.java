package com.money.exception;

public class NotSufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Long accountId;

	public NotSufficientBalanceException(Long accountId) {
		this.accountId = accountId;
	}

	@Override
	public String getMessage() {
		return "Account " + accountId + " does not have sufficient balance to process";
	}

}
