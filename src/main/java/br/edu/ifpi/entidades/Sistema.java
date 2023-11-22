package br.edu.ifpi.entidades;

import java.util.Scanner;

import br.edu.ifpi.DAO.AlunoDao;
import br.edu.ifpi.DAO.Conexao;
import br.edu.ifpi.DAO.CursoDao;
import br.edu.ifpi.DAO.ProfessorDao;

public class Sistema {
    Conexao conexao;

    public void carregarSistema(){
        Conexao conexao = new Conexao();
        mostrarMenuInicial();
    }

    public void mostrarMenuInicial(){
        System.out.println("___________M E N U   I N I C I A L___________");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Logar Usuário");
        System.out.println("0. Sair do Sistema");
        System.out.println("_____________________________________________");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha uma opção:");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1){
            cadastraUsuario();
        } else if (opt == 2){
            logarUsuario();
        }else if(opt == 0){
            System.out.println("Obrigado por usar o Sistema de Cursos Online JTM!");
            System.exit(0);
        }else{
            System.out.println("Opção Inválida, tente Novamente");
            mostrarMenuInicial();
        }
    }

    public void cadastraUsuario(){
        Conexao conexao = new Conexao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o tipo de Usuário:");
        System.out.println("1. Aluno");
        System.out.println("2. Professor");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1){
            System.out.println("Digite seu Nome:");
            String nome = scanner.nextLine();
            System.out.println("Digite seu Email:");
            String email = scanner.nextLine();
            
            AlunoDao AlunorDao = new AlunoDao(conexao);

            Aluno aluno = new Aluno(tipo, nome, email, null);
            AlunoDao.cadastrar(aluno);
        } else if (tipo == 2){
            System.out.println("Digite seu Nome:");
            String nome = scanner.nextLine();
            System.out.println("Digite seu Email:");
            String email = scanner.nextLine();
            
            ProfessorDao ProfessorDao = new ProfessorDao(conexao);

            Professor professor = new Professor(nome, tipo, email, null);
            ProfessorDao.cadastrar(professor);
        } 
    }
    
    public void logarUsuario(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu Nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite seu ID:");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();

        /* bucar pelo id e identificar o tipo, se for aluno = 1, prof = 2, adm = 3 */

        if (tipo == 1){
            menuAluno();
        } else if (tipo == 2){
            menuProfessor();
        } else {
            System.out.println("Usuário Não Encontrado, tente novamente.");
            logarUsuario();
        }

    }

    public void menuAluno(){
        Scanner scanner = new Scanner(System.in);
        Conexao conexao = new Conexao();

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

        if(opt == 1){
            CursoDao cursodao = new CursoDao();
            CursoDao.consultarTodos();
        } else if (opt == 2){
            ProfessorDao professordao = new ProfessorDao(conexao);
            ProfessorDao.consultarTodos();
        } else if (opt == 3){

        } else if (opt == 4){

        }else if (opt == 0){
            mostrarMenuInicial();
        } else {
            System.out.println("Opção Inválida, tente novamente.");
            menuAluno();
        }
    }

    public void menuProfessor(){
        Scanner scanner = new Scanner(System.in);
        Conexao conexao = new Conexao();

        System.out.println("______M E N U   D O   P R O F E S S O R______");
        System.out.println("\n1. Vizualizar lista de Cursos");
        System.out.println("2. Vizualizar lista de Professores");
        System.out.println("3. Vizualizar lista de Alunos");
        System.out.println("4. Realizar cadastro em Curso");
        System.out.println("5. Registrar Notas de Alunos");
        System.out.println("0. Sair");
        System.out.println("_____________________________________________");
        System.out.println("\nEscolha uma opção: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if(opt == 1){
            CursoDao cursodao = new CursoDao();
            CursoDao.consultarTodos();
        } else if (opt == 2){
            ProfessorDao professordao = new ProfessorDao(conexao);
            ProfessorDao.consultarTodos();
            menuProfessor();
        } else if (opt == 3){

        } else if (opt == 4){

        }else if (opt == 5){
            
        }else if (opt == 0){
            mostrarMenuInicial();
        } else {
            System.out.println("Opção Inválida, tente novamente.");
            menuProfessor();
        }
    }
}

