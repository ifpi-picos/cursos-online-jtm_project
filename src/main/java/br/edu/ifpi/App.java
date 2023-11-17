package br.edu.ifpi;

import java.util.Scanner;

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
        System.out.println("M E N U   I N I C I A L");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Logar Usuário");

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

        System.out.println("Digite seu Nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite seu email:");
        String email = scanner.nextLine();

        Usuario usuario = null;

        if (usuario == null) {
            System.out.println("Erro ao cadastrar o usuário!");
            mostrarMenuInicial();
        } else {
            UsuarioDAO dao = new UsuarioDAO();
            if (dao.cadastrar(usuario) > 0) {
                System.out.println("Usuário cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar o usuário!");
            }
            mostrarMenuInicial();
        }
    }
}