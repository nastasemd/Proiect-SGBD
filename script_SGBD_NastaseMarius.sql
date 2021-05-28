-- NastaseMarius_tabel_1 
    -- are doar cheie primara
    -- nu are coloane fara duplicate
-- NastaseMarius_tabel_2 
    -- are doar cheie primara
    -- are coloane fara duplicate
-- NastaseMarius_tabel_3
	-- are o cheie semantica pe langa cea primara

--
-- Stergerea tabelelor
--

DROP TABLE NastaseMarius_tabel_1;
DROP TABLE NastaseMarius_tabel_2;
DROP TABLE NastaseMarius_tabel_3;

--
-- Crearea tabelelor
--

CREATE TABLE NastaseMarius_tabel_1(
    id_tabel NUMBER(2) PRIMARY KEY,
    numar NUMBER(2) NOT NULL,
    nume VARCHAR2(10) NOT NULL,
    prenume VARCHAR2(10) NOT NULL
);

CREATE TABLE NastaseMarius_tabel_2(
    id_tabel NUMBER(2) PRIMARY KEY,
    numar NUMBER(2) NOT NULL,
    nume VARCHAR2(10) NOT NULL,
    prenume VARCHAR2(10) NOT NULL
);

CREATE TABLE NastaseMarius_tabel_3(
    id_tabel NUMBER(2) PRIMARY KEY,
    numar NUMBER(2) NOT NULL,
    nume VARCHAR2(10) NOT NULL,
    prenume VARCHAR2(10),
	CONSTRAINT UC_Persoana UNIQUE (nume,prenume)
);

--
-- Inserare date
--

INSERT INTO NastaseMarius_tabel_1 (id_tabel, numar, nume, prenume) VALUES (1, 1, 'Ionescu', 'Ion');
INSERT INTO NastaseMarius_tabel_1 (id_tabel, numar, nume, prenume) VALUES (2, 2, 'Popescu', 'Maria');
INSERT INTO NastaseMarius_tabel_1 (id_tabel, numar, nume, prenume) VALUES (3, 2, 'Popescu', 'Ion');

INSERT INTO NastaseMarius_tabel_2 (id_tabel, numar, nume, prenume) VALUES (1, 3, 'Ionescu', 'Vasile');
INSERT INTO NastaseMarius_tabel_2 (id_tabel, numar, nume, prenume) VALUES (2, 1, 'Marinescu', 'Maria');
INSERT INTO NastaseMarius_tabel_2 (id_tabel, numar, nume, prenume) VALUES (3, 2, 'Popescu', 'Marius');

INSERT INTO NastaseMarius_tabel_3 (id_tabel, numar, nume, prenume) VALUES (1, 3, 'Mihai', 'Vasile');
INSERT INTO NastaseMarius_tabel_3 (id_tabel, numar, nume, prenume) VALUES (2, 1, 'Mircea', 'Ion');
INSERT INTO NastaseMarius_tabel_3 (id_tabel, numar, nume, prenume) VALUES (3, 2, 'Popescu', 'George');

COMMIT;