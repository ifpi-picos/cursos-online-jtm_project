package br.edu.ifpi;

import br.edu.ifpi.entidades.Sistema;

public class App {
    public static void main(String[] args) {
        System.out.println("Bem Vindo ao Sistema de Cursos Online JTM");
        Sistema sistema = new Sistema();
        sistema.mostrarMenuInicial();
    }

}
