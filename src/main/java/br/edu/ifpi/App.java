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
        System.out.println("Escolha o tipo de Usuário:");
        System.out.println("1. Aluno");
        System.out.println("2. Professor");
        System.out.println("3. Administrador");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1){
            
        } else if (tipo == 2){
            
        } else if (tipo == 3){
            
        }
    }
    
}
