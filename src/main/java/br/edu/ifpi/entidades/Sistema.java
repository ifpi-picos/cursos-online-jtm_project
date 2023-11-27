package br.edu.ifpi.entidades;

import java.sql.SQLException;
import java.util.Scanner;

import br.edu.ifpi.DAO.AlunoDao;
import br.edu.ifpi.DAO.AutenticacaoDao;
import br.edu.ifpi.DAO.Conexao;
import br.edu.ifpi.DAO.CursoDao;
import br.edu.ifpi.DAO.ProfessorDao;
import br.edu.ifpi.DAO.TurmaDao;
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

            Aluno aluno = new Aluno(0, nome, email);
            alunoDao.cadastrar(aluno);
            menuAluno();
        } else if (tipo == 2) {
            System.out.println("Digite Nome do Professor:");
            String nome = scanner.nextLine();
            System.out.println("Digite Email do Professor:");
            String email = scanner.nextLine();

            Professor professor = new Professor(nome, 0, email, 0);
            professorDao.cadastrar(professor);
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
        AlunoDao alunoDao = new AlunoDao(conexao);
        CursoDao cursoDao = new CursoDao(conexao);
        ProfessorDao professorDao = new ProfessorDao(conexao);
        TurmaDao turmaDao = new TurmaDao(conexao);

        System.out.println("________M E N U   D O   A L U N O________");
        System.out.println("\n1. Vizualizar Perfil");
        System.out.println("2. Vizualizar lista de Cursos");
        System.out.println("3. Vizualizar lista de Professores");
        System.out.println("4. Vizualizar Notas");
        System.out.println("5. Realizar cadastro em Curso");
        System.out.println("0. Sair");
        System.out.println("_____________________________________________");
        System.out.println("\nEscolha uma opção: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1) {
            System.out.println("Confirme seu Email: ");
            String email = scanner.nextLine();
            alunoDao.vizualizarPerfilAluno(email);
            menuAluno();
        } else if (opt == 2){
            cursoDao.consultarTodos();
            menuAluno();
        } else if (opt == 3) {
            professorDao.consultarTodos();
            menuAluno();
        } else if (opt == 4) {
            System.out.println("Confirme seu Id: ");
            int id = scanner.nextInt();

            turmaDao.mostrarBoletim(id);
            menuAluno();
        } else if (opt == 5) {
            System.out.println("Confirme seu ID:");
            int aluno = scanner.nextInt();
            System.out.println("Insira o ID do Curso:");
            int curso = scanner.nextInt();

            Turma turma = new Turma(aluno, curso, 0);
            turmaDao.cadastrar(turma);
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
        ProfessorDao professorDao = new ProfessorDao(conexao);

        System.out.println("______M E N U   D O   P R O F E S S O R______");
        System.out.println("\n1. Vizualizar Perfil");
        System.out.println("2. Cursos");
        System.out.println("3. Professores");
        System.out.println("4. Alunos");
        System.out.println("0. Sair");
        System.out.println("_____________________________________________");
        System.out.println("\nEscolha uma opção: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1) {
            System.out.println("Confirme seu email:");
            String email = scanner.nextLine();

            professorDao.vizualizarPerfilProfessor(email);
            menuProfessor();
        } else if (opt == 2){
            cursos();
        } else if (opt == 3) {
            professores();
        } else if (opt == 4) {
            alunos();
        } else if (opt == 0) {
            mostrarMenuInicial();
        } else {
            System.out.println("Opção Inválida, tente novamente.");
            menuProfessor();
        }
    }

    public void cursos() {
        Scanner scanner = new Scanner(System.in);
        Conexao conexao = new Conexao();
        CursoDao cursoDao = new CursoDao(conexao);
        System.out.println("_________________C U R S O S________________");
        System.out.println("\n1. Vizualizar lista de Cursos");
        System.out.println("2. Realizar cadastro de Curso");
        System.out.println("3. Editar dados de um Curso");
        System.out.println("4. Excluir um Curso");
        System.out.println("0. Voltar ao menu Professor");
        System.out.println("_____________________________________________");
        System.out.println("\nEscolha uma opção: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1) {
            cursoDao.consultarTodos();
            cursos();
        } else if (opt == 2) {
            System.out.println("Digite Nome do Curso:");
            String nome = scanner.nextLine();
            System.out.println("Digite Carga Horária do Curso:");
            String ch = scanner.nextLine();
            StatusCurso status = StatusCurso.ABERTO;

            Curso curso = new Curso(0, nome, status, ch);
            cursoDao.cadastrar(curso);
            cursos();
        } else if (opt == 3) {
            System.out.print("Digite o ID do curso a ser alterado: ");
            int alteracao = scanner.nextInt();
            scanner.nextLine();

            System.out.println("novo nome: ");
            String nome = scanner.nextLine();
            System.out.println("nova carga horaria: ");
            String cargaHoraria = scanner.nextLine();

            Curso cursoalterado = new Curso(alteracao, nome, null, cargaHoraria);
            System.out.println("status do curso: ");
            System.out.println("1. Aberto");
            System.out.println("2. Fechado");
            int opcao = scanner.nextInt();
            if (opcao == 1) {
                cursoalterado.setStatus(StatusCurso.ABERTO);
            } else if (opcao == 2) {
                cursoalterado.setStatus(StatusCurso.FECHADO);
            } else {
                System.out.println("Opção inválida, Tente Novamente:");
                System.out.println("status do curso: ");
                System.out.println("1. Aberto");
                System.out.println("2. Fechado");
                opcao = scanner.nextInt();
            }

            cursoDao.alterar(cursoalterado);

            cursos();
        } else if (opt == 4) {
            System.out.println("Digite ID do Curso:");
            int id = scanner.nextInt();
            StatusCurso status = StatusCurso.ABERTO;

            Curso curso = new Curso(id , null, status, null);
            cursoDao.remover(curso);
            cursos();
        }else if (opt == 0) {
            menuProfessor();
        } else {
            System.out.println("Opção Inválida, tente novamente.");
            professores();
        }
    }

    public void professores() {
        Scanner scanner = new Scanner(System.in);
        Conexao conexao = new Conexao();
        ProfessorDao professorDao = new ProfessorDao(conexao);
        System.out.println("____________P R O F E S S O R E S____________");
        System.out.println("\n1. Vizualizar lista de Professoers");
        System.out.println("2. Realizar cadastro de um Professor");
        System.out.println("3. Editar dados de um Professor");
        System.out.println("4. Excluir um Professor");
        System.out.println("5. Matricular Professor em um Curso");
        System.out.println("0. Voltar ao menu Professor");
        System.out.println("_____________________________________________");
        System.out.println("\nEscolha uma opção: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1) {
            professorDao.consultarTodos();
            professores();
        } else if (opt == 2) {
            System.out.println("Digite Nome do Professor:");
            String nome = scanner.nextLine();
            System.out.println("Digite Email do Professor:");
            String email = scanner.nextLine();

            Professor professor = new Professor(nome, 0, email, 0);
            professorDao.cadastrar(professor);
            professores();
        } else if (opt == 3) {
            System.out.println("Digite o ID do Professor que deseja Editar: ");
            int Id_alter = scanner.nextInt();
            scanner.nextLine();

            Professor alterarProf = new Professor(null, 0, null, 0);

            alterarProf.setId(Id_alter);

            System.out.println("Novo Nome: ");
            alterarProf.setNome(scanner.nextLine());

            System.out.println("Novo Email: ");
            alterarProf.setEmail(scanner.next());

            professorDao.alterar(alterarProf);

            professores();
        } else if (opt == 4) {
            System.out.println("Digite o ID do Professor:");
            int id = scanner.nextInt();
            
            Professor professor = new Professor(null, id, null, 0);
            professorDao.remover(professor);
            professores();
        }else if (opt == 5) {
            System.out.println("Digite o ID do Professor:");
            int professor = scanner.nextInt();
            System.out.println("Digite o ID do Curso:");
            int curso = scanner.nextInt();

            professorDao.AssociarProfessorCurso(professor, curso);
            professores();
        } else if (opt == 0) {
            menuProfessor();
        } else {
            System.out.println("Opção Inválida, tente novamente.");
            professores();
        }
    }

    public void alunos() {
        Scanner scanner = new Scanner(System.in);
        Conexao conexao = new Conexao();
        AlunoDao alunoDao = new AlunoDao(conexao);
        TurmaDao turmaDao = new TurmaDao(conexao);
        System.out.println("_________________A L U N O S_________________");
        System.out.println("\n1. Vizualizar lista de Alunos");
        System.out.println("2. Realizar cadastro de um Aluno");
        System.out.println("3. Editar dados de um Aluno");
        System.out.println("4. Excluir um Aluno");
        System.out.println("5. Matricular Aluno em um Curso");
        System.out.println("6. Registrar Notas de Alunos");
        System.out.println("0. Voltar ao menu Professor");
        System.out.println("_____________________________________________");
        System.out.println("\nEscolha uma opção: ");
        int opt = scanner.nextInt();
        scanner.nextLine();

        if (opt == 1) {
            alunoDao.consultarTodos();
            alunos();
        } else if (opt == 2) {
            System.out.println("Digite Nome do Aluno:");
            String nome = scanner.nextLine();
            System.out.println("Digite Email do Aluno:");
            String email = scanner.nextLine();

            Aluno aluno = new Aluno(0, nome, email);
            alunoDao.cadastrar(aluno);
            alunos();
        } else if (opt == 3) {
            System.out.println("Digite o ID do Aluno que deseja Editar: ");
            int Id = scanner.nextInt();
            scanner.nextLine();

            Aluno alterarAluno = new Aluno(Id, null, null);

            alterarAluno.setId(Id);

            System.out.println("Novo Nome: ");
            alterarAluno.setNome(scanner.nextLine());
            System.out.println("Novo Email: ");
            alterarAluno.setEmail(scanner.nextLine());

            alunoDao.alterar(alterarAluno);

            alunos();
        } else if (opt == 4) {
            System.out.println("Digite o ID do Aluno:");
            int id = scanner.nextInt();
            
            Aluno aluno = new Aluno(id, null, null);
            alunoDao.remover(aluno);
            alunos();
        }else if (opt == 5) {
            System.out.println("Digite o ID do Aluno:");
            int aluno = scanner.nextInt();
            System.out.println("Digite o ID do Curso:");
            int curso = scanner.nextInt();

            Turma turma = new Turma(aluno, curso, 0);
            turmaDao.cadastrar(turma);

            alunos();
        } else if (opt == 6) {
            System.out.println("Digite o ID do Curso:");
            int idCurso = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Digite o ID do Aluno para o qual deseja registrar as notas:");
            int idAluno = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Digite a nota do aluno: ");
            float nota = scanner.nextFloat();

            Turma novaTurma = new Turma(idCurso, idAluno, nota);
            novaTurma.setNota(nota);

            int resultado = turmaDao.cadastrar(novaTurma);
            if (resultado > 0) {
                System.out.println("Notas do aluno registradas com sucesso!");
            } else {
                System.out.println("Falha ao registrar as notas do aluno.");
            }
            alunos();
        } else if (opt == 0) {
            menuProfessor();
        } else {
            System.out.println("Opção Inválida, tente novamente.");
            alunos();
        }
    }
}
