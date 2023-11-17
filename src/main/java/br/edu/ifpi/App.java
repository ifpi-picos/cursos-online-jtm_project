package br.edu.ifpi;

import java.util.Scanner;

import br.edu.ifpi.DAO.AlunoDao;
import br.edu.ifpi.DAO.ProfessorDao;
import br.edu.ifpi.entidades.Administrador;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.entidades.Usuario;

public class App {
    public static void main(String[] args) {
        System.out.println("Bem Vindo ao Sistema de Cursos Online JTM");
        mostrarMenuInicial();
    }
    
    public static void mostrarMenuInicial(){
        System.out.println("___________M E N U   I N I C I A L___________");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Logar Usuário");
        System.out.println("_____________________________________________");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha uma opção:");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1){
            cadastraUsuario();
        } else if (opt == 2){

        }else{
            System.out.println("Opção Inválida, tente Novamente");
            mostrarMenuInicial();
        }
    }

    public static void cadastraUsuario(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o tipo de Usuário:");
        System.out.println("1. Aluno");
        System.out.println("2. Professor");
        System.out.println("3. Administrador");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1){
            System.out.println("Digite seu Nome:");
            String nome = scanner.nextLine();
            System.out.println("Digite seu Email:");
            String email = scanner.nextLine();

            Aluno aluno = new Aluno(nome, tipo, email, null);
            AlunoDao.cadastrar(aluno);
        } else if (tipo == 2){
            System.out.println("Digite seu Nome:");
            String nome = scanner.nextLine();
            System.out.println("Digite seu Email:");
            String email = scanner.nextLine();

            Professor professor = new Professor(nome, tipo, email, null);
            ProfessorDao.cadastrar(professor);
        } else if (tipo == 3){
            System.out.println("Digite seu Nome:");
            String nome = scanner.nextLine();
            System.out.println("Digite seu Email:");
            String email = scanner.nextLine();
            
            Administrador administrador = new Administrador(nome, tipo, email);
            AdministradorDao.cadastrar(administrador);
        }
    }
    
    public static void logarUsuario(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu Nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite seu ID:");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();

    }

    public static void menuAluno(){
        System.out.println("________M E N U   D O   A L U N O________");
        System.out.println("\n1. Vizualizar lista de Cursos");
        System.out.println("2. Vizualizar lista de Professores");
        System.out.println("3. Vizualizar Notas");
        System.out.println("4. Realizar cadastro em Curso");
        System.out.println("5. Sair");
        System.out.println("_____________________________________________");
    }

    public static void menuProfessor(){
        System.out.println("______M E N U   D O   P R O F E S S O R______");
        System.out.println("\n1. Vizualizar lista de Cursos");
        System.out.println("2. Vizualizar lista de Professores");
        System.out.println("3. Vizualizar lista de Alunos");
        System.out.println("4. Realizar cadastro em Curso");
        System.out.println("5. Registrar Notas de Alunos");
        System.out.println("6. Sair");
        System.out.println("_____________________________________________");
    }

    public static void menuAdministrador(){
        System.out.println("___M E N U   D O   A D M I N S T R A D O R___");
        System.out.println("\n1. Vizualizar lista de Cursos");
        System.out.println("2. Cadastrar Curso");
        System.out.println("3. Editar Curso");
        System.out.println("4. Excluir Curso");
        System.out.println("5. Vizualizar lista de Professores");
        System.out.println("6. Cadastrar Professor");
        System.out.println("7. Editar Professor");
        System.out.println("8. Excluir Professor");
        System.out.println("9. Vizualizar lista de Alunos");
        System.out.println("10. Cadastrar Aluno");
        System.out.println("11. Editar Aluno");
        System.out.println("12. Excluir Aluno");
        System.out.println("13. Sair");
        System.out.println("_____________________________________________");
    }
}
