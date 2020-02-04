package com.money.dao;

import com.money.model.tables.records.AccountRecord;

public interface AccountDAO {

	AccountRecord newAccount();

	AccountRecord save(AccountRecord record);

	AccountRecord getById(Long accountId);

	AccountRecord getForUpdate(Long accountId);

}
