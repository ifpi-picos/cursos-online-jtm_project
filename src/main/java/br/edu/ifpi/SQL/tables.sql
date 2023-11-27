create table
public.aluno (
id serial,
nome character varying(50) not null,
email character varying(50) not null,
constraint aluno_pkey primary key (id)
) tablespace pg_default;

create table
public.professor (
id serial,
nome character varying(50) not null,
email character varying(50) not null,
id_curso integer null,
constraint professor_pkey primary key (id),
constraint professor_id_curso_fkey foreign key (id_curso) references curso (id),
) tablespace pg_default;

create table
public.curso (
id serial,
nome character varying(50) not null,
status character varying(50) not null,
cargahoraria character varying not null,
constraint curso_pkey primary key (id)
) tablespace pg_default;

create table
public.turma (
id serial,
id_curso integer null,
id_aluno integer null,
notas double precision null,
situacao character varying(10) null,
CONSTRAINT turma_pkey PRIMARY KEY (id),
CONSTRAINT turma_id_aluno_fkey FOREIGN KEY (id_aluno) REFERENCES aluno (id) ON DELETE CASCADE,
CONSTRAINT turma_id_curso_fkey FOREIGN KEY (id_curso) REFERENCES curso (id) ON DELETE CASCADE
) TABLESPACE pg_default;