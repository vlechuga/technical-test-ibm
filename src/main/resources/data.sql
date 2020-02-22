
INSERT INTO client (client_id, name, address, city, phone)
VALUES
    (1000, 'Victor Lechuga', 'Venezuela', 'Maracaibo', '+574124705619'),
    (1001, 'Manuel Lechuga', 'Venezuela', 'Maracaibo', '+574124705619'),
    (1002, 'Neo Lechuga', 'Venezuela', 'Maracaibo', '+574124705619');

INSERT INTO adviser (name, specialty)
VALUES
    ('Victor Lechuga', 'Programer'),
    ('Manuel Lechuga', 'Contador');

INSERT INTO card (card_id, number, type, client_id)
VALUES
    (1000, '1111222233334444', 'VISA', 1000),
    (1001, '1111222233335555', 'VISA', 1001),
    (1002, '1111222233336666', 'VISA', 1002);

INSERT INTO audit (description, amount, creation_date, card_id)
VALUES
    ('Trx 1', 1000000, CURRENT_TIMESTAMP, 1000),
    ('Trx 2', 100, CURRENT_TIMESTAMP, 1001),
    ('Trx 3', 5000, CURRENT_TIMESTAMP, 1002);
