CREATE TABLE TRANSACTION_TYPE (
                                  ENUMERATION                     VARCHAR2(100) NOT NULL,
                                  "VALUE"                         VARCHAR2(200) NOT NULL,
                                  CONSTRAINT PK_TRANSACTION_TYPE PRIMARY KEY (ENUMERATION)
);

CREATE TABLE TRANSACTION (
                             TRANSACTION_ID                  VARCHAR2(100) NOT NULL,
                             OPERATION_ID                    VARCHAR2(100) NOT NULL,
                             ACCOUNTING_DATE                 DATE,
                             VALUE_DATE                      DATE,
                             TYPE                            VARCHAR2(100),
                             AMOUNT                          NUMBER NOT NULL,
                             CURRENCY                        VARCHAR2(10) NOT NULL,
                             DESCRIPTION                     VARCHAR2(100),
                             CONSTRAINT PK_TRANSACTION PRIMARY KEY (TRANSACTION_ID),
                             CONSTRAINT FK_TRANSACTION_TYPE             FOREIGN KEY (TYPE) REFERENCES TRANSACTION_TYPE (ENUMERATION)
);
