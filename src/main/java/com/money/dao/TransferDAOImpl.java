package com.money.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import com.google.inject.Inject;
import com.money.exception.AccountNotFoundException;
import static com.money.model.Tables.ACCOUNT;
import static com.money.model.Tables.TRANSFER;

import com.money.model.Tables;
import com.money.model.tables.records.TransferRecord;
import com.money.type.TransferType;
import com.money.util.DatabaseManager;

public class TransferDAOImpl implements TransferDAO {

	private final DSLContext dslContext;

	@Inject
	public TransferDAOImpl(DatabaseManager databaseManager) {
		this.dslContext = databaseManager.getDSLContext();
	}

	public TransferRecord newTransfer() {
		return dslContext.newRecord(TRANSFER);
	}

	@Override
	public TransferRecord save(TransferRecord transferRecord) {
		transferRecord.store();
		return transferRecord;
	}

	@Override
	public TransferRecord getById(Long trannsferId) {
		return dslContext.selectFrom(TRANSFER).where(Tables.TRANSFER.ID.eq(trannsferId)).fetchOne();
	}

	@Override
	public TransferRecord deposit(Long accountId, BigDecimal amount) throws AccountNotFoundException {
		TransferRecord transferRecord = dslContext.transactionResult(configuration -> {
			DSLContext context = DSL.using(configuration);
			processDeposit(accountId, amount, context);

			return createTransfer(TransferType.DEPOSIT, amount, null, accountId, context);
		});

		return transferRecord;
	}

	@Override
	public TransferRecord withdraw(Long accountId, BigDecimal amount) throws AccountNotFoundException {
		TransferRecord transferRecord = dslContext.transactionResult(configuration -> {
			DSLContext context = DSL.using(configuration);
			processWithdraw(accountId, amount, context);

			return createTransfer(TransferType.WITHDRAW, amount, accountId, null, context);
		});

		return transferRecord;
	}

	@Override
	public TransferRecord transfer(Long fromAccountId, Long toAccountId, BigDecimal amount)
			throws AccountNotFoundException {
		TransferRecord transferRecord = dslContext.transactionResult(configuration -> {
			DSLContext context = DSL.using(configuration);

			processWithdraw(fromAccountId, amount, context);
			processDeposit(toAccountId, amount, context);

			return createTransfer(TransferType.TRANSFER, amount, fromAccountId, toAccountId, context);
		});

		return transferRecord;
	}

	private void processWithdraw(Long accountId, BigDecimal amount, DSLContext context) throws AccountNotFoundException {
		int result = context.update(ACCOUNT).set(ACCOUNT.BALANCE, ACCOUNT.BALANCE.subtract(amount))
				.set(ACCOUNT.REC_VERSION, ACCOUNT.REC_VERSION.plus(1)).where(ACCOUNT.ID.eq(accountId)).execute();
		if (result == 0) {
			throw new AccountNotFoundException(accountId);
		}
	}

	private void processDeposit(Long accountId, BigDecimal amount, DSLContext context) throws AccountNotFoundException {
		int result = context.update(ACCOUNT).set(ACCOUNT.BALANCE, ACCOUNT.BALANCE.add(amount))
				.set(ACCOUNT.REC_VERSION, ACCOUNT.REC_VERSION.plus(1)).where(ACCOUNT.ID.eq(accountId)).execute();
		if (result == 0) {
			throw new AccountNotFoundException(accountId);
		}
	}

	private TransferRecord createTransfer(TransferType transferType, BigDecimal amount, Long fromAccountId,
			Long toAccountId, DSLContext context) {

		TransferRecord transferRecord = context
				.insertInto(TRANSFER, TRANSFER.TRANSFER_TYPE, TRANSFER.AMOUNT, TRANSFER.FROM_ACCOUNT_ID, TRANSFER.TO_ACCOUNT_ID,
						TRANSFER.CREATED_DATE, TRANSFER.REC_VERSION)
				.values(transferType.name(), amount, fromAccountId, toAccountId, Timestamp.valueOf(LocalDateTime.now()), 1)
				.returning().fetchOne();

		return transferRecord;
	}

}
