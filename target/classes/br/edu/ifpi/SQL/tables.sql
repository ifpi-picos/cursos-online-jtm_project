CREATE TABLE aluno(
    ID serial NOT NULL,
    NOME varchar (30) NOT NULL,
    EMAIL varchar (25) NOT NULL,
    CURSO varchar (30) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE curso(
    ID serial NOT NULL,
    NOME varchar (30) NOT NULL,
    -- STATUS ENUM 
    CARGAHORARIA INTEGER NOT NULL,
    PROF_ID INTEGER NOT NULL,
    PRIMARY KEY (ID), 
    FOREIGN KEY (PROF_ID) REFERENCES professor(id)
    
);