package com.money.service;

import com.google.inject.Inject;
import com.money.dao.AccountDAO;
import com.money.dto.AccountRequestDTO;
import com.money.dto.AccountResponseDTO;
import com.money.exception.AccountNotFoundException;
import com.money.model.tables.records.AccountRecord;

public class AccountServiceImpl implements AccountService {

	private final AccountDAO accountDAO;

	@Inject
	public AccountServiceImpl(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
		AccountRecord record = accountDAO.newAccount();
		record.setBalance(accountRequestDTO.getBalance());
		record.setUsername(accountRequestDTO.getUserName());
		AccountRecord created = save(record);
		AccountResponseDTO responseDTO = new AccountResponseDTO();
		responseDTO.setId(created.getId());
		return responseDTO;
	}

	@Override
	public AccountResponseDTO getAccount(Long accountId) throws AccountNotFoundException {
		AccountRecord accountRecord = accountDAO.getById(accountId);
		if (accountRecord != null) {
			AccountResponseDTO responseDTO = new AccountResponseDTO();
			responseDTO.setId(accountRecord.getId());
			responseDTO.setUserName(accountRecord.getUsername());
			responseDTO.setBalance(accountRecord.getBalance());
			return responseDTO;
		}
		throw new AccountNotFoundException(accountId);
	}

	@Override
	public AccountRecord getAccountForUpdate(Long accountId) throws AccountNotFoundException {
		AccountRecord record = accountDAO.getForUpdate(accountId);
		if (record == null) {
			throw new AccountNotFoundException(accountId);
		}
		return record;
	}

	@Override
	public AccountRecord save(AccountRecord accountRecord) {
		return accountDAO.save(accountRecord);
	}

}
