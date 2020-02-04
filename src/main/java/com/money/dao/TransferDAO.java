package com.money.dao;

import java.math.BigDecimal;

import com.money.exception.AccountNotFoundException;
import com.money.model.tables.records.TransferRecord;

public interface TransferDAO {

	TransferRecord newTransfer();

	TransferRecord save(TransferRecord transferRecord);

	TransferRecord deposit(Long accountId, BigDecimal amount) throws AccountNotFoundException;

	TransferRecord withdraw(Long accountId, BigDecimal amount) throws AccountNotFoundException;

	TransferRecord transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) throws AccountNotFoundException;

	TransferRecord getById(Long trannsferId);

}
