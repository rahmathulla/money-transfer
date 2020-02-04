package com.money.service;

import com.money.dto.AccountRequestDTO;
import com.money.dto.AccountResponseDTO;
import com.money.exception.AccountNotFoundException;
import com.money.model.tables.records.AccountRecord;

public interface AccountService {

	AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);

	AccountResponseDTO getAccount(Long accountId) throws AccountNotFoundException;

	AccountRecord getAccountForUpdate(Long accountId) throws AccountNotFoundException;

	AccountRecord save(AccountRecord accountRecord);

}
