package com.money.service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import com.google.inject.Inject;
import com.money.dao.TransferDAO;
import com.money.dto.TransferResponseDTO;
import com.money.exception.AccountNotFoundException;
import com.money.exception.NotSufficientBalanceException;
import com.money.model.tables.records.AccountRecord;
import com.money.model.tables.records.TransferRecord;

public class TransferServiceImpl implements TransferService {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

	private AccountService accountService;
	private final TransferDAO transferDAO;

	@Inject
	public TransferServiceImpl(AccountService accountService, TransferDAO transferDAO) {
		this.accountService = accountService;
		this.transferDAO = transferDAO;
	}

	@Override
	public TransferResponseDTO deposit(Long accountId, BigDecimal amount) {
		TransferRecord transferRecord = transferDAO.deposit(accountId, amount);
		return geTransferResponseDTO(transferRecord, false);

	}

	@Override
	public TransferResponseDTO withdraw(Long accountId, BigDecimal amount) throws NotSufficientBalanceException {
		checkWithdrawPossible(accountId, amount);
		TransferRecord transferRecord = transferDAO.withdraw(accountId, amount);
		return geTransferResponseDTO(transferRecord, false);
	}

	@Override
	public TransferResponseDTO transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
		checkWithdrawPossible(fromAccountId, amount);
		TransferRecord transferRecord = transferDAO.transfer(fromAccountId, toAccountId, amount);
		return geTransferResponseDTO(transferRecord, true);

	}

	@Override
	public TransferResponseDTO getTransfer(Long transferId) {
		TransferRecord transferRecord = transferDAO.getById(transferId);
		if (transferRecord != null) {
			return geTransferResponseDTO(transferRecord, true);
		}
		throw new AccountNotFoundException(transferId);
	}

	private void checkWithdrawPossible(Long accountId, BigDecimal amount) throws NotSufficientBalanceException {
		AccountRecord account = accountService.getAccountForUpdate(accountId);

		BigDecimal balance = account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO;
		if (balance.compareTo(amount) < 0) {
			throw new NotSufficientBalanceException(accountId);
		}

	}

	private TransferResponseDTO geTransferResponseDTO(TransferRecord transferRecord, boolean isAccountDetailsNeeded) {
		TransferResponseDTO transferResponseDTO = new TransferResponseDTO();
		transferResponseDTO.setId(transferRecord.getId());
		transferResponseDTO.setTransferType(transferRecord.getTransferType());
		transferResponseDTO.setAmount(transferRecord.getAmount());
		transferResponseDTO.setCreatedDate(transferRecord.getCreatedDate().toLocalDateTime().format(formatter));
		if (isAccountDetailsNeeded) {
			transferResponseDTO.setFromAccountId(transferRecord.getFromAccountId());
			transferResponseDTO.setToAccountId(transferRecord.getToAccountId());
		}
		return transferResponseDTO;

	}

}
