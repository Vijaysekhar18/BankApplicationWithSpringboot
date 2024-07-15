CREATE TABLE bank.BANK_ACCCOUNT (
    id VARCHAR(255) PRIMARY KEY,
    account_number VARCHAR(255) NOT NULL,
    balance FLOAT NOT NULL,
    owner VARCHAR(255) NOT NULL
);

ALTER TABLE bank.BANK_ACCCOUNT
ADD owner_Phone_number BIGINT NOT NULL DEFAULT 2345678901;

CREATE TABLE bank.USER (
    first_Name VARCHAR(255) NOT NULL,
    last_Name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    phone_Number BIGINT NOT NULL,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (phone_Number)
);

ALTER TABLE bank.BANK_ACCCOUNT
ADD CONSTRAINT FK_Phone_Number
FOREIGN KEY (owner_Phone_number) REFERENCES bank.USER(phone_Number);

-- DATA
INSERT INTO bank.BANK_ACCCOUNT (id, account_number, balance, owner) VALUES
('1', 'ACC123456', 2500.75, 'John Doe'),
('2', 'ACC123457', 1500.50, 'Jane Smith'),
('3', 'ACC123458', 3200.00, 'Alice Johnson'),
('4', 'ACC123459', 4500.25, 'Robert Brown'),
('5', 'ACC123460', 2700.10, 'Maria Garcia'),
('6', 'ACC123461', 3800.90, 'James Wilson'),
('7', 'ACC123462', 2100.30, 'Linda Martinez'),
('8', 'ACC123463', 1900.85, 'David Anderson'),
('9', 'ACC123464', 5000.00, 'Susan Lee'),
('10', 'ACC123465', 3400.60, 'Michael Thompson');
COMMIT;


INSERT INTO bank.USER (first_Name, last_Name, age, phone_Number, address) VALUES
('Vijay', 'Doe', 30, 1234567890, '123 Main St, Springfield'),
('Sai', 'Smith', 25, 2345678901, '456 Elm St, Metropolis'),
('Harish', 'Johnson', 35, 3456789012, '789 Oak St, Gotham'),
('Kumar', 'Brown', 40, 4567890123, '101 Pine St, Star City'),
('Sekhar', 'Garcia', 28, 5678901234, '202 Maple St, Central City');
COMMIT;