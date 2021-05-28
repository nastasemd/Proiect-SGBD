--
-- Tabele (3 coloane fiecare)
    -- tip date
        -- col1 - cheie primara
        -- col2 - NUMBER(2)
        -- col3 - VARCHAR2(10)
--
-- NichiforVlad_tabel_1 
    -- are o constrangere de existenta
-- NichiforVlad_tabel_2 
    -- nu are constrangere de existenta
    -- nu are coloane cu campuri complet completate
-- NichiforVlad_tabel_3
    -- nu are constrangere de existenta
    -- are coloane cu campuri complet completate


--
-- Stergerea tabelelor
--

DROP TABLE NichiforVlad_tabel_1;
DROP TABLE NichiforVlad_tabel_2;
DROP TABLE NichiforVlad_tabel_3;


--
-- Crearea tabelelor
--

CREATE TABLE NichiforVlad_tabel_1(
    id_tabel NUMBER(2) PRIMARY KEY,
    numar NUMBER(2) NOT NULL,
    text VARCHAR2(10)
);

CREATE TABLE NichiforVlad_tabel_2(
    id_tabel NUMBER(2) PRIMARY KEY,
    numar NUMBER(2),
    text VARCHAR2(10)
);

CREATE TABLE NichiforVlad_tabel_3(
    id_tabel NUMBER(2) PRIMARY KEY,
    numar NUMBER(2),
    text VARCHAR2(10)
);

--
-- Inserare date
--

INSERT INTO NichiforVlad_tabel_1 (id_tabel, numar, text) VALUES (1, 1, 'text1');
INSERT INTO NichiforVlad_tabel_1 (id_tabel, numar, text) VALUES (2, 2, NULL);
INSERT INTO NichiforVlad_tabel_1 (id_tabel, numar, text) VALUES (3, 3, 'text3');

INSERT INTO NichiforVlad_tabel_2 (id_tabel, numar, text) VALUES (1, 1, 'text1');
INSERT INTO NichiforVlad_tabel_2 (id_tabel, numar, text) VALUES (2, NULL, 'text2');
INSERT INTO NichiforVlad_tabel_2 (id_tabel, numar, text) VALUES (3, 2, NULL);

INSERT INTO NichiforVlad_tabel_3 (id_tabel, numar, text) VALUES (1, NULL, 'text1');
INSERT INTO NichiforVlad_tabel_3 (id_tabel, numar, text) VALUES (2, 1, 'text2');
INSERT INTO NichiforVlad_tabel_3 (id_tabel, numar, text) VALUES (3, 1, 'text3');

COMMIT;