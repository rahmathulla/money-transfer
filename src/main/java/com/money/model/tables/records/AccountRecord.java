/*
 * This file is generated by jOOQ.
 */
package com.money.model.tables.records;


import com.money.model.tables.Account;

import java.math.BigDecimal;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountRecord extends UpdatableRecordImpl<AccountRecord> implements Record4<Long, String, BigDecimal, Integer> {

    private static final long serialVersionUID = -566297666;

    /**
     * Setter for <code>MONEY_TRANSFER.ACCOUNT.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>MONEY_TRANSFER.ACCOUNT.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>MONEY_TRANSFER.ACCOUNT.USERNAME</code>.
     */
    public void setUsername(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>MONEY_TRANSFER.ACCOUNT.USERNAME</code>.
     */
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>MONEY_TRANSFER.ACCOUNT.BALANCE</code>.
     */
    public void setBalance(BigDecimal value) {
        set(2, value);
    }

    /**
     * Getter for <code>MONEY_TRANSFER.ACCOUNT.BALANCE</code>.
     */
    public BigDecimal getBalance() {
        return (BigDecimal) get(2);
    }

    /**
     * Setter for <code>MONEY_TRANSFER.ACCOUNT.REC_VERSION</code>.
     */
    public void setRecVersion(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>MONEY_TRANSFER.ACCOUNT.REC_VERSION</code>.
     */
    public Integer getRecVersion() {
        return (Integer) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, BigDecimal, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, String, BigDecimal, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Account.ACCOUNT.ID;
    }

    @Override
    public Field<String> field2() {
        return Account.ACCOUNT.USERNAME;
    }

    @Override
    public Field<BigDecimal> field3() {
        return Account.ACCOUNT.BALANCE;
    }

    @Override
    public Field<Integer> field4() {
        return Account.ACCOUNT.REC_VERSION;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getUsername();
    }

    @Override
    public BigDecimal component3() {
        return getBalance();
    }

    @Override
    public Integer component4() {
        return getRecVersion();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getUsername();
    }

    @Override
    public BigDecimal value3() {
        return getBalance();
    }

    @Override
    public Integer value4() {
        return getRecVersion();
    }

    @Override
    public AccountRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public AccountRecord value2(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public AccountRecord value3(BigDecimal value) {
        setBalance(value);
        return this;
    }

    @Override
    public AccountRecord value4(Integer value) {
        setRecVersion(value);
        return this;
    }

    @Override
    public AccountRecord values(Long value1, String value2, BigDecimal value3, Integer value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountRecord
     */
    public AccountRecord() {
        super(Account.ACCOUNT);
    }

    /**
     * Create a detached, initialised AccountRecord
     */
    public AccountRecord(Long id, String username, BigDecimal balance, Integer recVersion) {
        super(Account.ACCOUNT);

        set(0, id);
        set(1, username);
        set(2, balance);
        set(3, recVersion);
    }
}
