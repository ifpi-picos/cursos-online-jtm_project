package br.edu.ifpi.entidades;

import java.sql.SQLException;
import java.util.Scanner;

import br.edu.ifpi.DAO.AlunoDao;
import br.edu.ifpi.DAO.AutenticacaoDao;
import br.edu.ifpi.DAO.Conexao;
import br.edu.ifpi.DAO.CursoDao;
import br.edu.ifpi.DAO.ProfessorDao;

public class Sistema {
    Conexao conexao;

    public void carregarSistema() {
        conexao = new Conexao(); // Correção: Use a variável de classe existente
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
            System.out.println("Digite Curso do Aluno:");
            String curso = scanner.nextLine();

            AlunoDao alunoDao = new AlunoDao(conexao);

            Aluno aluno = new Aluno(0, nome, email, curso);
            alunoDao.cadastrar(aluno);
            menuAluno();
        } else if (tipo == 2) {
            System.out.println("Digite Nome do Professor:");
            String nome = scanner.nextLine();
            System.out.println("Digite Email do Professor:");
            String email = scanner.nextLine();

            ProfessorDao professorDao = new ProfessorDao(conexao);

            Professor professor = new Professor(nome, 0, email, null);
            professorDao.cadastrar(professor);
            menuProfessor();
        }
    }

    public void logarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione o tipo de Usuário:");
        System.out.println("1. Aluno");
        System.out.println("2. Professor");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite seu Email:");
        String email = scanner.nextLine();
        System.out.println("Digite seu ID:");
        int id = scanner.nextInt();
        scanner.nextLine();

        AutenticacaoDao autenticacao = new AutenticacaoDao(conexao);
        try {
            autenticacao.autenticarAluno(email, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            autenticacao.autenticarProfessor(email, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (tipo == 1) {
            menuAluno();
        } else if (tipo == 2) {
            menuProfessor();
        } else {
            System.out.println("Usuário Não Encontrado, tente novamente.");
            logarUsuario();
        }
    }

    public void menuAluno() {
        Scanner scanner = new Scanner(System.in);

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
            CursoDao cursoDao = new CursoDao(conexao);
            cursoDao.consultarTodos();
            menuAluno();
        } else if (opt == 2) {
            ProfessorDao professorDao = new ProfessorDao(conexao);
            professorDao.consultarTodos();
            menuAluno();
        } else if (opt == 3) {
            // Adicione lógica para visualizar notas
            menuAluno();
        } else if (opt == 4) {
            // Adicione lógica para cadastrar em curso
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
            CursoDao cursoDao = new CursoDao(conexao);
            cursoDao.consultarTodos();
            menuProfessor();
        } else if (opt == 2) {
            // Adicione lógica para cadastrar curso
            menuProfessor();
        } else if (opt == 3) {
            // Adicione lógica para editar curso
            menuProfessor();
        } else if (opt == 4) {
            ProfessorDao professorDao = new ProfessorDao(conexao);
            professorDao.consultarTodos();
            menuProfessor();
        } else if (opt == 5) {
            // Adicione lógica para visualizar lista de alunos
            menuProfessor();
        } else if (opt == 6) {
            // Adicione lógica para cadastrar aluno
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
