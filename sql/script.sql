CREATE SCHEMA VEM_SER;

SET SCHEMA VEM_SER;

-- -----------------------------------------------------
-- Table CATEGORY
-- -----------------------------------------------------
CREATE TABLE  CATEGORY (
  id_category NUMBER NOT NULL,
  name VARCHAR2(45) UNIQUE NOT NULL,
  category_description VARCHAR2(100) NOT NULL,
  
  PRIMARY KEY (id_category)
);
	CREATE SEQUENCE category_seq
	 START    1
	 INCREMENT   1;	
	
-- -----------------------------------------------------
-- Table BANK_ACCOUNT
-- -----------------------------------------------------
CREATE TABLE  BANK_ACCOUNT (
  id_bank_account NUMBER NOT NULL,
  account_number VARCHAR2(7) NOT NULL UNIQUE,
  agency VARCHAR2(4) NOT NULL,
  
  PRIMARY KEY (id_bank_account)
);
	CREATE SEQUENCE bank_account_seq
	 START    1
	 INCREMENT   1;

-- -----------------------------------------------------
-- Table USER
-- -----------------------------------------------------
CREATE TABLE  USERS (
  id_user NUMBER NOT NULL,
  name VARCHAR2(45) NOT NULL,
  email VARCHAR2(70) NOT NULL,
  password VARCHAR2(2000) NOT NULL,
  type NUMBER NOT NULL,
  document VARCHAR2(18) NOT NULL,
  
  PRIMARY KEY (id_user)
);
	CREATE SEQUENCE users_seq
	 START    1
	 INCREMENT   1;
		
-- -----------------------------------------------------
-- Table REQUEST
-- -----------------------------------------------------
CREATE TABLE  REQUEST (
  id_request NUMBER NOT NULL,
  title VARCHAR2(45) NOT NULL,
  request_description VARCHAR2(2000) NOT NULL,
  goal NUMBER NOT NULL,
  reached_value NUMBER NOT NULL,
  id_category NUMBER NOT NULL,
  id_bank_account NUMBER NOT NULL,
  id_user NUMBER NOT NULL,
  
  PRIMARY KEY (id_request),
  CONSTRAINT fk_Request_Category
    FOREIGN KEY (id_category)
    REFERENCES CATEGORY (id_category),
  CONSTRAINT fk_Request_BankAccount1
    FOREIGN KEY (id_bank_account)
    REFERENCES BANK_ACCOUNT (id_bank_account),
  CONSTRAINT fk_REQUEST_USER1
    FOREIGN KEY (id_user)
    REFERENCES USERS (id_user)
);
	CREATE SEQUENCE request_seq
	 START     1
	 INCREMENT   1;	

-- -----------------------------------------------------
-- Table DONATE
-- -----------------------------------------------------
CREATE TABLE  DONATE (
  id_donate NUMBER NOT NULL,
  id_request NUMBER NOT NULL,
  donator_name VARCHAR2(45) NOT NULL,
  donator_email VARCHAR2(70) NOT NULL,
  donate_value NUMBER NOT NULL,
  donate_description VARCHAR2(2000),
  
  PRIMARY KEY (id_donate),
  CONSTRAINT fk_Request
    FOREIGN KEY (id_request)
    REFERENCES REQUEST (id_request)

);
	CREATE SEQUENCE donate_seq
	 START     1
	 INCREMENT   1;
	 
-- -----------------------------------------------------
-- INSERT CATEGORY
-- -----------------------------------------------------	
INSERT INTO CATEGORY (id_category , name, category_description) 
VALUES (nextval("category_seq"), 'COMBATE_A_FOME', 'Combate a Fome');
INSERT INTO CATEGORY (id_category , name, category_description) 
VALUES (nextval("category_seq"), 'CRIANÇAS', 'Crianças');
INSERT INTO CATEGORY (id_category , name, category_description) 
VALUES (nextval("category_seq"), 'ENFERMOS', 'Enfermos');
INSERT INTO CATEGORY (id_category , name, category_description) 
VALUES (nextval("category_seq"), 'COMBATE_A_COVID_19', 'Combate a COVID-19');
INSERT INTO CATEGORY (id_category , name, category_description) 
VALUES (nextval("category_seq"), 'CAUSAS_AMBIENTAIS', 'Causas Ambientais');
INSERT INTO CATEGORY (id_category , name, category_description) 
VALUES (nextval("category_seq"), 'SOBREVIVENTES_DE_GUERRA', 'Sobreviventes de Guerra');
INSERT INTO CATEGORY (id_category , name, category_description) 
VALUES (nextval("category_seq"), 'ANIMAIS', 'Animais');
INSERT INTO CATEGORY (id_category , name, category_description) 
VALUES (nextval("category_seq"), 'SONHOS', 'Sonhos');
INSERT INTO CATEGORY (id_category , name, category_description) 
VALUES (nextval("category_seq"), 'POBREZA', 'Pobreza');
INSERT INTO CATEGORY (id_category , name, category_description) 
VALUES (nextval("category_seq"), 'OUTROS', 'Outros');

-- -----------------------------------------------------
-- INSERT BANK_ACCOUNT
-- -----------------------------------------------------
INSERT INTO BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (nextval("bank_account_seq"), '2202512', '1005');
INSERT INTO BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (nextval("bank_account_seq"), '1010256', '1008');
INSERT INTO BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (nextval("bank_account_seq"), '7020855', '1205');
INSERT INTO BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (nextval("bank_account_seq"), '210252', '1005');
INSERT INTO BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (nextval("bank_account_seq"), '1810256', '1008');
INSERT INTO BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (nextval("bank_account_seq"), '7900255', '1205');
	
-- -----------------------------------------------------
-- INSERT USER
-- -----------------------------------------------------
INSERT  INTO USERS (id_user, name, email, password, type, document)
VALUES (nextval("users_seq"), 'Daniele', 'dani@gmail', '1234', 1, '123.456.789-00');
INSERT  INTO USERS (id_user, name, email, password, type, document)
VALUES (nextval("users_seq"), 'Liane', 'liane@gmail', '1234', 1, '123.456.789-01');
INSERT  INTO USERS (id_user, name, email, password, type, document)
VALUES (nextval("users_seq"), 'Claudia', 'claudia@gmail', '1234', 1, '123.456.789-02');
INSERT  INTO USERS (id_user, name, email, password, type, document)
VALUES (nextval("users_seq"), 'Rodrigo', 'rodrigo@gmail', '1234', 2, '11.111.111/1111-11');

-- -----------------------------------------------------
-- INSERT REQUEST
-- -----------------------------------------------------
INSERT INTO REQUEST (id_request, title, request_description, goal, reached_value, id_category, id_bank_account, id_user)
VALUES (nextval("request_seq"), 'Criança faminta', ' Não temos dinheiro para alimentar nossa filha de 3 anos', 50000, 0, 2, 1, 1);
INSERT INTO REQUEST (id_request, title, request_description, goal, reached_value, id_category, id_bank_account, id_user)
VALUES (nextval("request_seq"), 'Meu pai está morrendo', ' Não temos dinheiro para os remédios', 100000, 0, 3, 2, 2);
INSERT INTO REQUEST (id_request, title, request_description, goal, reached_value, id_category, id_bank_account, id_user)
VALUES (nextval("request_seq"), 'Anne não tem fámilia, país e comida', ' Uma refugiada Ucraniana de 7 anos, precisamos de dinheiro pra manter a ong', 200000, 0, 6, 3, 3);

-- -----------------------------------------------------
-- INSERT DONATE
-- -----------------------------------------------------	
INSERT INTO DONATE (id_donate, id_request , donator_name, donator_email, donate_value, donate_description)
VALUES (nextval("donate_seq"), 1, 'Ana', 'ana@gmail.com', 500.0, 'Boa sorte');
INSERT INTO DONATE (id_donate, id_request ,donator_name, donator_email, donate_value, donate_description)
VALUES (nextval("donate_seq"), 3, 'Nicolas', 'nicolas@gmail.com', 100.0, 'melhoras');
INSERT INTO DONATE (id_donate, id_request , donator_name, donator_email, donate_value, donate_description)
VALUES (nextval("donate_seq"), 2, 'Lucas', 'lucas@gmail.com', 100.0, NULL);