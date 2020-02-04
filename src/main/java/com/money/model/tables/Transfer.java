/*
 * This file is generated by jOOQ.
 */
package com.money.model.tables;


import com.money.model.Indexes;
import com.money.model.Keys;
import com.money.model.MoneyTransfer;
import com.money.model.tables.records.TransferRecord;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Transfer extends TableImpl<TransferRecord> {

    private static final long serialVersionUID = -935084185;

    /**
     * The reference instance of <code>MONEY_TRANSFER.TRANSFER</code>
     */
    public static final Transfer TRANSFER = new Transfer();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TransferRecord> getRecordType() {
        return TransferRecord.class;
    }

    /**
     * The column <code>MONEY_TRANSFER.TRANSFER.ID</code>.
     */
    public final TableField<TransferRecord, Long> ID = createField(DSL.name("ID"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>MONEY_TRANSFER.TRANSFER.TO_ACCOUNT_ID</code>.
     */
    public final TableField<TransferRecord, Long> TO_ACCOUNT_ID = createField(DSL.name("TO_ACCOUNT_ID"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>MONEY_TRANSFER.TRANSFER.FROM_ACCOUNT_ID</code>.
     */
    public final TableField<TransferRecord, Long> FROM_ACCOUNT_ID = createField(DSL.name("FROM_ACCOUNT_ID"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>MONEY_TRANSFER.TRANSFER.AMOUNT</code>.
     */
    public final TableField<TransferRecord, BigDecimal> AMOUNT = createField(DSL.name("AMOUNT"), org.jooq.impl.SQLDataType.DECIMAL.nullable(false), this, "");

    /**
     * The column <code>MONEY_TRANSFER.TRANSFER.TRANSFER_TYPE</code>.
     */
    public final TableField<TransferRecord, String> TRANSFER_TYPE = createField(DSL.name("TRANSFER_TYPE"), org.jooq.impl.SQLDataType.VARCHAR(256), this, "");

    /**
     * The column <code>MONEY_TRANSFER.TRANSFER.CREATED_DATE</code>.
     */
    public final TableField<TransferRecord, Timestamp> CREATED_DATE = createField(DSL.name("CREATED_DATE"), org.jooq.impl.SQLDataType.TIMESTAMP.precision(6).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>MONEY_TRANSFER.TRANSFER.REC_VERSION</code>.
     */
    public final TableField<TransferRecord, Integer> REC_VERSION = createField(DSL.name("REC_VERSION"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>MONEY_TRANSFER.TRANSFER</code> table reference
     */
    public Transfer() {
        this(DSL.name("TRANSFER"), null);
    }

    /**
     * Create an aliased <code>MONEY_TRANSFER.TRANSFER</code> table reference
     */
    public Transfer(String alias) {
        this(DSL.name(alias), TRANSFER);
    }

    /**
     * Create an aliased <code>MONEY_TRANSFER.TRANSFER</code> table reference
     */
    public Transfer(Name alias) {
        this(alias, TRANSFER);
    }

    private Transfer(Name alias, Table<TransferRecord> aliased) {
        this(alias, aliased, null);
    }

    private Transfer(Name alias, Table<TransferRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Transfer(Table<O> child, ForeignKey<O, TransferRecord> key) {
        super(child, key, TRANSFER);
    }

    @Override
    public Schema getSchema() {
        return MoneyTransfer.MONEY_TRANSFER;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PRIMARY_KEY_7);
    }

    @Override
    public Identity<TransferRecord, Long> getIdentity() {
        return Keys.IDENTITY_TRANSFER;
    }

    @Override
    public UniqueKey<TransferRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_7;
    }

    @Override
    public List<UniqueKey<TransferRecord>> getKeys() {
        return Arrays.<UniqueKey<TransferRecord>>asList(Keys.CONSTRAINT_7);
    }

    @Override
    public TableField<TransferRecord, Integer> getRecordVersion() {
        return REC_VERSION;
    }

    @Override
    public Transfer as(String alias) {
        return new Transfer(DSL.name(alias), this);
    }

    @Override
    public Transfer as(Name alias) {
        return new Transfer(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Transfer rename(String name) {
        return new Transfer(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Transfer rename(Name name) {
        return new Transfer(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, Long, Long, BigDecimal, String, Timestamp, Integer> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}