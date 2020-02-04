
CREATE TABLE money_transfer.account (
  id BIGINT auto_increment primary key,
  userName VARCHAR(256) NOT NULL,
  balance DECIMAL NOT NULL DEFAULT 0,
  REC_VERSION INTEGER  NOT NULL DEFAULT 0
);

CREATE TABLE money_transfer.transfer (
  id BIGINT auto_increment primary key,
  to_account_id BIGINT,
  from_account_id BIGINT,
  amount DECIMAL NOT NULL,
  transfer_type VARCHAR(256),
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  REC_VERSION INTEGER  NOT NULL DEFAULT 0
);