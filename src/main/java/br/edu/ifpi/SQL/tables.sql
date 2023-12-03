create table
public.aluno (
    id serial,
    nome character varying(50) not null,
    email character varying(50) not null,
    constraint aluno_pkey primary key (id)
) tablespace pg_default;

create table
public.curso (
    id serial,
    nome character varying(50) not null,
    status character varying(50) not null,
    cargahoraria character varying not null,
    quantidade_alunos integer null default 0,
    constraint curso_pkey primary key (id)
) tablespace pg_default;

create table
public.cursoaluno (
    id serial,
    id_curso integer null,
    id_aluno integer null,
    notas double precision null,
    situacao character varying(10) null,
    aproveitamento character varying null,
    constraint cursoaluno_pkey primary key (id),
    constraint cursoaluno_id_aluno_fkey foreign key (id_aluno) references aluno (id),
    constraint cursoaluno_id_curso_fkey foreign key (id_curso) references curso (id)
) tablespace pg_default;

create table
public.professor (
    id serial,
    nome character varying(50) not null,
    email character varying(50) not null,
    id_curso integer null,
    constraint professor_pkey primary key (id)
) tablespace pg_default;