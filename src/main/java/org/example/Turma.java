package org.example;
import java.util.List;
import java.util.ArrayList;

public class Turma {
    private List<Aluno> alunos;
    private Professor responsavel;
    private String NomeDisciplina;
    private String codigoTurma;
    private String codigoDisciplina;
    private String semestre;
    private int creditos;
    private String horario1;
    private String horario2;
    private PlanoDeEnsino planoDeEnsino;

    public Turma(Professor responsavel, String nomeDisclina, String codigoTurma, String semestre, int creditos, String horario1, String horario2, PlanoDeEnsino planoDeEnsino, String codigoDisciplina) {
        this.responsavel = responsavel;
        NomeDisciplina = nomeDisclina;
        this.codigoTurma = codigoTurma;
        this.semestre = semestre;
        this.creditos = creditos;
        this.horario1 = horario1;
        this.horario2 = horario2;
        this.codigoDisciplina = codigoDisciplina;
        this.planoDeEnsino = planoDeEnsino;

        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        if(!alunos.contains(aluno)) {
            alunos.add(aluno);
        }

        DisciplinaCursada novaDisciplina = new DisciplinaCursada(
                this.NomeDisciplina,
                this.semestre,
                this.creditos,
                this.codigoDisciplina,
                this.codigoTurma,
                this.horario1,
                this.horario2,
                this.planoDeEnsino
        );

        aluno.matricularNaDisciplina(novaDisciplina);
        System.out.printf("Aluno %s foi matriculado em %s", aluno.getNome(), NomeDisciplina);
    }

    public void setFalta(Aluno aluno) {
        if (alunos.contains(aluno)) {
            DisciplinaCursada ficha = aluno.buscarDisciplina(this.codigoDisciplina);
            if (ficha != null) {
                ficha.adicionarFalta();
            }
        }
    }

    public void setNota(Aluno aluno, double nota, String tipoNota) {
        if (alunos.contains(aluno)) {
            // Busca a ficha da disciplina DENTRO do aluno
            DisciplinaCursada ficha = aluno.buscarDisciplina(this.codigoDisciplina);

            if (ficha != null) {
                switch (tipoNota.toUpperCase()) {
                    case "N1": ficha.adicionarN1(nota); break;
                    case "N2": ficha.adicionarN2(nota); break;
                    case "AI": ficha.adicionarAI(nota); break;
                    default: System.out.println("Tipo de nota inválido (Use N1, N2 ou AI)");
                }
                System.out.printf("Nota %s lançada para %s.\n", tipoNota, aluno.getNome());
            }
        } else {
            System.out.println("Aluno não pertence a esta turma.");
        }
    }

    public Professor getResponsavel() {
        return responsavel;
    }

    public String getNomeDisciplina() {
        return NomeDisciplina;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}
