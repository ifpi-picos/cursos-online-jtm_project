package br.edu.ifpi.entidades;

import java.sql.SQLException;
import java.util.Scanner;

import br.edu.ifpi.DAO.AlunoDao;
import br.edu.ifpi.DAO.AutenticacaoDao;
import br.edu.ifpi.DAO.Conexao;
import br.edu.ifpi.DAO.CursoDao;
import br.edu.ifpi.DAO.ProfessorDao;
import br.edu.ifpi.enums.StatusCurso;

public class Sistema {
    Conexao conexao;

    public void carregarSistema() {
        conexao = new Conexao();
        mostrarMenuInicial();
    }

    public void mostrarMenuInicial() {
        System.out.println("___________M E N U   I N I C I A L___________");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Logar Usuário");
        System.out.println("0. Sair do Sistema");
        System.out.println("_____________________________________________");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha uma opção:");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1) {
            cadastraUsuario();
        } else if (opt == 2) {
            logarUsuario();
        } else if (opt == 0) {
            System.out.println("Obrigado por usar o Sistema de Cursos Online JTM!");
            System.exit(0);
        } else {
            System.out.println("Opção Inválida, tente Novamente");
            mostrarMenuInicial();
        }
    }

    public void cadastraUsuario() {
        Scanner scanner = new Scanner(System.in);
        Conexao conexao = new Conexao();
        AlunoDao alunoDao = new AlunoDao(conexao);
        ProfessorDao professorDao = new ProfessorDao(conexao);

        System.out.println("_____________________________________________");
        System.out.println("Escolha o tipo de Usuário:");
        System.out.println("1. Aluno");
        System.out.println("2. Professor");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.println("Digite Nome do Aluno:");
            String nome = scanner.nextLine();
            System.out.println("Digite Email do Aluno:");
            String email = scanner.nextLine();

            Aluno aluno = new Aluno(0, nome, email, null);
            alunoDao.cadastrar(aluno);
            System.out.println("_____________________________________________\n");
            menuAluno();
        } else if (tipo == 2) {
            System.out.println("Digite Nome do Professor:");
            String nome = scanner.nextLine();
            System.out.println("Digite Email do Professor:");
            String email = scanner.nextLine();

            Professor professor = new Professor(nome, 0, email, null);
            professorDao.cadastrar(professor);
            System.out.println("_____________________________________________\n");
            menuProfessor();
        }
    }

    public void logarUsuario() {
        Scanner scanner = new Scanner(System.in);
        Conexao conexao = new Conexao();
        System.out.println("Digite seu Email:");
        String email = scanner.nextLine();
        System.out.println("Digite seu ID:");
        int id = scanner.nextInt();
        scanner.nextLine();

    AutenticacaoDao autenticacao = new AutenticacaoDao(conexao);

    Aluno alunoAutenticado = null;
    Professor professorAutenticado = null;

    try {
        alunoAutenticado = autenticacao.autenticarAluno(email, id);
    } catch (SQLException e) {
        e.printStackTrace(); 
    }

    if (alunoAutenticado != null) {
        menuAluno();
    } else {
        try {
            professorAutenticado = autenticacao.autenticarProfessor(email, id);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

    if (professorAutenticado != null) {
        menuProfessor();
    } else {
        System.out.println("Usuário não encontrado");
        logarUsuario();
    }
}
    }

    public void menuAluno() {
        Scanner scanner = new Scanner(System.in);
        Conexao conexao = new Conexao();
        CursoDao cursoDao = new CursoDao(conexao);
        ProfessorDao professorDao = new ProfessorDao(conexao);

        System.out.println("________M E N U   D O   A L U N O________");
        System.out.println("\n1. Vizualizar lista de Cursos");
        System.out.println("2. Vizualizar lista de Professores");
        System.out.println("3. Vizualizar Notas");
        System.out.println("4. Realizar cadastro em Curso");
        System.out.println("0. Sair");
        System.out.println("_____________________________________________");
        System.out.println("\nEscolha uma opção: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1) {
            cursoDao.consultarTodos();
            menuAluno();
        } else if (opt == 2) {
            professorDao.consultarTodos();
            menuAluno();
        } else if (opt == 3) {

            menuAluno();
        } else if (opt == 4) {

            menuAluno();
        } else if (opt == 0) {
            mostrarMenuInicial();
        } else {
            System.out.println("Opção Inválida, tente novamente.");
            menuAluno();
        }
    }

    public void menuProfessor() {
        Scanner scanner = new Scanner(System.in);
        Conexao conexao = new Conexao();
        CursoDao cursoDao = new CursoDao(conexao);
        ProfessorDao professorDao = new ProfessorDao(conexao);
        AlunoDao alunoDao = new AlunoDao(conexao);

        System.out.println("______M E N U   D O   P R O F E S S O R______");
        System.out.println("\n1. Vizualizar lista de Cursos");
        System.out.println("2. Realizar cadastro de Curso");
        System.out.println("3. Editar dados de um Curso");
        System.out.println("4. Vizualizar lista de Professores");
        System.out.println("5. Vizualizar lista de Alunos");
        System.out.println("6. Realizar cadastro de Aluno");
        System.out.println("7. Editar dados de um Aluno");
        System.out.println("8. Registrar Notas de Alunos");
        System.out.println("9. Associar Professor a um Curso");
        System.out.println("0. Sair");
        System.out.println("_____________________________________________");
        System.out.println("\nEscolha uma opção: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1) {
            cursoDao.consultarTodos();
            menuProfessor();
        } else if (opt == 2) {
            System.out.println("Digite Nome do Curso:");
            String nome = scanner.nextLine();
            System.out.println("Digite Carga Horária do Curso:");
            String ch = scanner.nextLine();
            StatusCurso status = StatusCurso.ABERTO;

            Curso curso = new Curso(0, nome, status, ch);
            cursoDao.cadastrar(curso);
            menuProfessor();
        } else if (opt == 3) {

            menuProfessor();
        } else if (opt == 4) {
            professorDao.consultarTodos();
            menuProfessor();
        } else if (opt == 5) {
            alunoDao.consultarTodos();
            menuProfessor();
        } else if (opt == 6) {
            System.out.println("Digite Nome do Aluno:");
            String nome = scanner.nextLine();
            System.out.println("Digite Email do Aluno:");
            String email = scanner.nextLine();

            Aluno aluno = new Aluno(0, nome, email, null);
            alunoDao.cadastrar(aluno);
            menuProfessor();
        } else if (opt == 7) {
            // Adicione lógica para editar aluno
            menuProfessor();
        } else if (opt == 8) {
            // Adicione lógica para registrar notas
            menuProfessor();
        } else if (opt == 9) {
            // Adicione lógica para associar professor a um curso
            menuProfessor();
        } else if (opt == 0) {
            mostrarMenuInicial();
        } else {
            System.out.println("Opção Inválida, tente novamente.");
            menuProfessor();
        }
    }
}
