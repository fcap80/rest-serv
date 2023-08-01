CREATE TABLE TRANSACTION (
                             TRANSACTION_ID                  VARCHAR2(100) NOT NULL,
                             OPERATION_ID                    VARCHAR2(100) NOT NULL,
                             ACCOUNTING_DATE                 DATE,
                             VALUE_DATE                      DATE,
                             TYPE_ENUMERATION                            VARCHAR2(100),
                             TYPE_VALUE                            VARCHAR2(100),
                             AMOUNT                          NUMBER NOT NULL,
                             CURRENCY                        VARCHAR2(10) NOT NULL,
                             DESCRIPTION                     VARCHAR2(100),
                             CONSTRAINT PK_TRANSACTION PRIMARY KEY (TRANSACTION_ID)
);
