--
-- Tabel (2 coloane)
    -- tip date
        -- col1 - VARCHAR2(50)
        -- col2 - VARCHAR2(50)
--
-- angajati
    -- nu are cheie primara
    -- adaug o noua coloana numerica si pun constrangerea de cheie primara pe ea

DROP TABLE angajati;
DROP SEQUENCE angajati_seq;

CREATE TABLE angajati (
nume_angajat VARCHAR2(50),
email_angajat VARCHAR2(50)
);

INSERT INTO angajati (nume_angajat, email_angajat) VALUES ('Ionel', 'ionel@gmail.com');
INSERT INTO angajati (nume_angajat, email_angajat) VALUES ('Matei' , 'matei@gmail.com');
INSERT INTO angajati (nume_angajat, email_angajat) VALUES ('Mihai', 'mih@gmail.com');

COMMIT;