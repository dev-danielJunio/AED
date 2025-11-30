package org.example;

public class Professor extends Pessoa {

    public Professor(String nome, long CPF, int idade, boolean criveis, char sexo, int matricula) {
        super(nome, CPF, idade, criveis, sexo, matricula);
    }

    public void lancarNota(Turma turma, Aluno aluno, double nota, String tipo) {
        if (turma.getResponsavel() == this) {
            turma.setNota(aluno, nota, tipo);
        } else {
            System.out.printf("O professor %s não leciona essa turma!", getNome());
        }
    }

    public void lancarFalta(Turma turma, Aluno aluno) {
        if (turma.getResponsavel().equals(this)) {
            turma.setFalta(aluno);
        } else {
            System.out.printf("O professor %s não leciona essa turma!", getNome());
        }
    }
}