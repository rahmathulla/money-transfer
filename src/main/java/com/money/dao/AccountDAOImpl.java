package com.money.dao;

import org.jooq.DSLContext;

import com.google.inject.Inject;
import com.money.model.Tables;
import com.money.model.tables.records.AccountRecord;
import com.money.util.DatabaseManager;

public class AccountDAOImpl implements AccountDAO {

	private final DSLContext dslContext;

	@Inject
	public AccountDAOImpl(DatabaseManager databaseManager) {
		dslContext = databaseManager.getDSLContext();
	}

	@Override
	public AccountRecord newAccount() {
		return dslContext.newRecord(Tables.ACCOUNT);
	}

	@Override
	public AccountRecord save(AccountRecord record) {

		record.store();
		return record;
	}

	@Override
	public AccountRecord getById(Long accountId) {
		return dslContext.selectFrom(Tables.ACCOUNT).where(Tables.ACCOUNT.ID.eq(accountId)).fetchOne();
	}

	@Override
	public AccountRecord getForUpdate(Long accountId) {
		return dslContext.selectFrom(Tables.ACCOUNT).where(Tables.ACCOUNT.ID.eq(accountId)).forUpdate().fetchOne();
	}
}
