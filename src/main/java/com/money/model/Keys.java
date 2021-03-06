/*
 * This file is generated by jOOQ.
 */
package com.money.model;


import com.money.model.tables.Account;
import com.money.model.tables.Transfer;
import com.money.model.tables.records.AccountRecord;
import com.money.model.tables.records.TransferRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>MONEY_TRANSFER</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AccountRecord, Long> IDENTITY_ACCOUNT = Identities0.IDENTITY_ACCOUNT;
    public static final Identity<TransferRecord, Long> IDENTITY_TRANSFER = Identities0.IDENTITY_TRANSFER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AccountRecord> CONSTRAINT_E = UniqueKeys0.CONSTRAINT_E;
    public static final UniqueKey<TransferRecord> CONSTRAINT_7 = UniqueKeys0.CONSTRAINT_7;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<AccountRecord, Long> IDENTITY_ACCOUNT = Internal.createIdentity(Account.ACCOUNT, Account.ACCOUNT.ID);
        public static Identity<TransferRecord, Long> IDENTITY_TRANSFER = Internal.createIdentity(Transfer.TRANSFER, Transfer.TRANSFER.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AccountRecord> CONSTRAINT_E = Internal.createUniqueKey(Account.ACCOUNT, "CONSTRAINT_E", Account.ACCOUNT.ID);
        public static final UniqueKey<TransferRecord> CONSTRAINT_7 = Internal.createUniqueKey(Transfer.TRANSFER, "CONSTRAINT_7", Transfer.TRANSFER.ID);
    }
}
