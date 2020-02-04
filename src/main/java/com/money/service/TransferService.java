package com.money.service;

import java.math.BigDecimal;

import com.money.dto.TransferResponseDTO;

public interface TransferService {

	TransferResponseDTO deposit(Long accountId, BigDecimal amount);

	TransferResponseDTO withdraw(Long accountId, BigDecimal amount);

	TransferResponseDTO transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);

	TransferResponseDTO getTransfer(Long transferId);

}
