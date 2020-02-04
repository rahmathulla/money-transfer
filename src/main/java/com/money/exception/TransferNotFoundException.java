package com.money.exception;

public class TransferNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Long transferId;

	public TransferNotFoundException(Long transferId) {
		this.transferId = transferId;
	}

	@Override
	public String getMessage() {
		return "Could not find transfer with Id " + transferId;
	}

}
