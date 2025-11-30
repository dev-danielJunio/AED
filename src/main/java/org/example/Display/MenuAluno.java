package org.example.Display;

import org.example.Admin;
import org.example.Aluno;
import org.example.DisciplinaCursada;
import org.example.Pessoa;
import org.example.Sistema.SistemaEscola;

import java.util.Scanner;

public class MenuAluno {

    private SistemaEscola sistema;
    private Scanner sc = new Scanner(System.in);

    public MenuAluno(SistemaEscola sistema) {
        this.sistema = sistema;
    }

    public void loginAluno() {
        System.out.println("\n-- Login Aluno --");
        System.out.print("Matrícula: ");
        int mat;
        try{
            mat = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Matrícula inválida.");
            return;
        }
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Pessoa p = sistema.login(mat, senha);

        if (p == null || !(p instanceof Aluno)) {
            System.out.println("❌ Matrícula ou senha incorretas!");
            return;
        } else{
            System.out.println("✅Login Realizado com Sucesso!");
        }

        mostrar((Aluno)p);
    }

    public void mostrar(Aluno aluno) {
        int opc = -1;

        do {
            System.out.println("\n=== Menu do Aluno ===");
            System.out.println("1 - Consultar Boletos");
            System.out.println("2 - Consultar Semestre Atual");
            System.out.println("3 - Consultar Histórico Escolar");
            System.out.println("4 - Matricular em Disciplina");
            System.out.println("5 - Consultar Meus Horários");
            System.out.println("6 - Buscar Disciplina (por código)");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opc = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida — digite um número.");
                continue;
            }

            switch (opc) {

                case 1:
                    aluno.consultarBoleto();
                    break;

                case 2:
                    aluno.consultarSemestre();
                    break;

                case 3:
                    aluno.consultarHistorico();
                    break;

                case 4:
                    matricularDisciplina(aluno);
                    break;

                case 5:
                    aluno.consultarHorarios();
                    break;

                case 6:
                    buscarDisciplina(aluno);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opc != 0);
    }

    private void matricularDisciplina(Aluno aluno) {
        System.out.println("Implementar");
    }

    private void buscarDisciplina(Aluno aluno) {
        System.out.println("Implementar");
    }
}
