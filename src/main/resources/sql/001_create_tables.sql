-- Create vo_plan table
CREATE TABLE "vo_plan"
(
    "PLAN_NUMBER"                   VARCHAR(255) PRIMARY KEY,
    "PLAN_NAME"                     VARCHAR(255),
    "LOAN_TYPE"                     VARCHAR(255),
    "POLICY_NUMBER"                 VARCHAR(255),
    "PLAN_EFFECTIVE_DATE"           DATE,
    "PRODUCT_CODE"                  VARCHAR(255),
    "MIN_SUM_ASSURED"               NUMERIC,
    "MAX_SUM_ASSURED"               NUMERIC,
    "MIN_AGE"                       INTEGER,
    "MAX_AGE"                       INTEGER,
    "INSURANCE_CLASS"               VARCHAR(255),
    "REFUND_ON_SURRENDER_FLAG"      VARCHAR(255),
    "COVER_BASIS"                   VARCHAR(255),
    "MIN_TERM"                      INTEGER,
    "MAX_TERM"                      INTEGER,
    "NML_QUESTIONNAIRE"             VARCHAR(255),
    "BELOW_ABOVE_NML_QUESTIONNAIRE" VARCHAR(255),
    "PLAN_START_DATE"               DATE,
    "PLAN_END_DATE"                 DATE,
    "BENEFIT_CODE"                  VARCHAR(255),
    "CREATED_BY"                    VARCHAR(255) DEFAULT 'SYSTEM_USER',
    "UPDATED_BY"                    VARCHAR(255) DEFAULT 'SYSTEM_USER',
    "CREATED_AT"                    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "UPDATED_AT"                    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Create VO_MEMBER_DETAILS table
CREATE TABLE "VO_MEMBER_DETAILS"
(
    "MEMBER_NUMBER"                   VARCHAR(255) PRIMARY KEY,
    "COI_NUMBER"                      VARCHAR(255),
    "PLAN_NUMBER"                     VARCHAR(255),
    "POLICY_NUMBER"                   VARCHAR(255),
    "PARTNER"                         VARCHAR(255),
    "EFFECTIVE_DATE"                  DATE,
    "MPH_ID"                          VARCHAR(255),
    "MEMBER_TYPE"                     VARCHAR(255),
    "LAN"                             VARCHAR(255),
    "CLIENT_NUMBER"                   VARCHAR(255),
    "SUM_ASSURED"                     INTEGER,
    "START_DATE"                      DATE,
    "DOB"                             DATE,
    "COVER_TYPE_FLAG"                 VARCHAR(255),
    "PREMIUM_TYPE"                    VARCHAR(255),
    "PREMIUM_FREQUENCY"               VARCHAR(255),
    "RISK_TERM"                       SMALLINT,
    "PREMIUM_TERM"                    INTEGER,
    "RISK_START_DATE"                 DATE,
    "RISK_END_DATE"                   DATE,
    "PREMIUM_START_DATE"              DATE,
    "PREMIUM_END_DATE"                DATE,
    "MEMBER_STATUS"                   VARCHAR(255),
    "EMPLOYEE_NO"                     VARCHAR(255),
    "OCCUPATION_CODE"                 INTEGER,
    "OCCUPATION_CLASS"                VARCHAR(255),
    "MEMBER_CATEGORY"                 VARCHAR(255),
    "AGE"                             SMALLINT,
    "UNDECISION_FLAG"                 INTEGER,
    "UNDECISION_DESC"                 VARCHAR(255),
    "EXCESS_SI"                       INTEGER,
    "SUM_INSURED_TYPE"                VARCHAR(255),
    "LOAN_AMOUNT"                     INTEGER,
    "LOAN_DISBURSEMENT_DATE"          DATE,
    "INFORMED_PREMIUM"                INTEGER,
    "TOP_UP_PREMIUM"                  INTEGER,
    "LOAN_TERM"                       INTEGER,
    "INTEREST_RATE"                   INTEGER,
    "MORATORIUM_FLAG"                 BOOLEAN,
    "INTEREST_PAID_DURING_MORATORIUM" INTEGER,
    "FUNDED_FLAG"                     INTEGER,
    "FIRST_NAME"                      VARCHAR(255),
    "LAST_NAME"                       VARCHAR(255),
    "TITLE"                           VARCHAR(255),
    "GENDER"                          VARCHAR(255),
    "EMAIL"                           VARCHAR(255),
    "PHONE_NUMBER"                    VARCHAR(255),
    "MARITAL_STATUS"                  VARCHAR(255),
    "ADDRESS_LINE1"                   VARCHAR(255),
    "ADDRESS_LINE2"                   VARCHAR(255),
    "ADDRESS_LINE3"                   VARCHAR(255),
    "CITY"                            VARCHAR(255),
    "STATE"                           VARCHAR(255),
    "PINCODE"                         VARCHAR(255),
    "PREMIUM_PAYABLE"                 INTEGER,
    "RELATIONSHIP_TO_CUSTOMER"        VARCHAR(255),
    "PERCENTAGE_ALLOCATION"           INTEGER,
    "RELATIONSHIP_TO_NOMINEE"         VARCHAR(255),
    "TRANSACTION_DATE"                DATE,
    "C_PERCENTAGE"                    SMALLINT,
    "S_PERCENTAGE"                    SMALLINT,
    "DEPENDENT_NUMBER"                SMALLINT,
    "NATIONALITY"                     VARCHAR(255),
    "CLAIMANT_ID"                     UUID,
    "CREATED_AT"                      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "UPDATED_AT"                      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_plan_number FOREIGN KEY ("PLAN_NUMBER") REFERENCES "vo_plan" ("PLAN_NUMBER")
);

-- Create DOCUMENT table
CREATE TABLE "DOCUMENT"
(
    "DOCUMENT_ID"   VARCHAR(255) PRIMARY KEY,
    "MEMBER_ID"     VARCHAR(255),
    "DOCUMENT_NAME" VARCHAR(255),
    "FILE_SIZE"     VARCHAR(255),
    "CLAIM_ID"      VARCHAR(255),
    "UPLOADED_BY"   VARCHAR(255),
    "OCR_AVAILABLE" BOOLEAN,
    "CREATED_BY"    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "UPDATED_DATE"  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Create claim table
CREATE TABLE "claim"
(
    "COI_NUMBER"                    VARCHAR(255) PRIMARY KEY,
    "CLAIMS_ID"                     VARCHAR(255),
    "LAN"                           VARCHAR(255),
    "POLICY_NUMBER"                 VARCHAR(255),
    "PRODUCT_CODE"                  VARCHAR(255),
    "CLAIM_TYPE"                    VARCHAR(255),
    "CAUSE_OF_EVENT"                VARCHAR(255),
    "DATE_OF_EVENT"                 DATE,
    "DATE_OF_REPORTING"             DATE,
    "DATE_OF_ACCIDENT_OR_DIAGNOSIS" DATE,
    "IS_MEMBER_DEAD"                VARCHAR(255),
    "SUM_ASSURED"                   NUMERIC,
    "ORIGINAL_AMOUNT_OF_LOAN"       NUMERIC,
    "OUTSTANDING_LOAN_AMOUNT"       NUMERIC,
    "RECOVERIES"                    NUMERIC,
    "BALANCE_CLAIM_AMOUNT"          NUMERIC,
    "DECLARATION1"                  VARCHAR(255),
    "DECLARATION2"                  VARCHAR(255),
    "DECLARATION3"                  VARCHAR(255),
    "CREATED_BY"                    VARCHAR(255) DEFAULT 'SYSTEM_USER',
    "UPDATED_BY"                    VARCHAR(255) DEFAULT 'SYSTEM_USER',
    "CREATED_AT"                    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "UPDATED_AT"                    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP NOT NULL
);


-- Drop VO_MEMBER_DETAILS table
DROP TABLE IF EXISTS "VO_MEMBER_DETAILS";

-- Drop vo_plan table
DROP TABLE IF EXISTS "vo_plan";

-- Drop DOCUMENT table
DROP TABLE IF EXISTS "DOCUMENT";

-- Drop claim table
DROP TABLE IF EXISTS "claim";