create table aluno (
id int not null primary key,
nome varchar(50) not null,
email varchar(50) not null
);

create table
professor (
id int not null primary key,
nome varchar(50) not null,
email varchar(50) not null
);

create table if not exists
curso (
id int not null primary key,
nome varchar(50) not null,
status varchar(50) not null,
cargaHoraria int not null,
foreign key (id_professor) REFERENCES professor(id)
);

CREATE TABLE turma(
id_curso int primary key,
id_aluno int primary key,
nota float(25),
situacao VARCHAR(255),
FOREIGN KEY (id_curso) REFERENCES curso(id),
FOREIGN key (id_aluno) REFERENCES aluno(id)
);
