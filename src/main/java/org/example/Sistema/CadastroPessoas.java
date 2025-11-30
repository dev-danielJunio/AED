package org.example.Sistema;

import org.example.*;

import java.util.ArrayList;
import java.util.List;

public class CadastroPessoas {

    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Turma> turmas = new ArrayList<>();

    // ---------------------
    // MÉTODOS DE ADIÇÃO
    // ---------------------
    public void addAluno(Aluno a) { alunos.add(a); }
    public void addProfessor(Professor p) { professores.add(p); }
    public void addAdmin(Admin a) { admins.add(a); }
    public void addTurma(Turma t) { turmas.add(t); }

    // ---------------------
    // MÉTODOS DE BUSCA
    // ---------------------
    public Pessoa buscarPorMatricula(int mat) {
        for (Aluno a : alunos) if (a.getMatricula() == mat) return a;
        for (Professor p : professores) if (p.getMatricula() == mat) return p;
        for (Admin a : admins) if (a.getMatricula() == mat) return a;
        return null;
    }

    public Aluno buscarAluno(int mat) {
        for (Aluno a : alunos)
            if (a.getMatricula() == mat)
                return a;
        return null;
    }

    public Professor buscarProfessor(int mat) {
        for (Professor p : professores)
            if (p.getMatricula() == mat)
                return p;
        return null;
    }

    public Admin buscarAdmin(int mat) {
        for (Admin a : admins)
            if (a.getMatricula() == mat)
                return a;
        return null;
    }

    public Turma buscarTurma(String codigo) {
        for (Turma t : turmas)
            if (t.getCodigoTurma().equalsIgnoreCase(codigo))
                return t;
        return null;
    }

    // ---------------------
    // LISTAGENS COMPLETAS
    // ---------------------
    public List<Aluno> getAlunos() { return alunos; }
    public List<Professor> getProfessores() { return professores; }
    public List<Admin> getAdmins() { return admins; }
    public List<Turma> getTurmas() { return turmas; }

    public void listarAlunos() {
        System.out.println("\n--- Lista de Alunos ---");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno a : alunos) {
            System.out.println("Nome: " + a.getNome() + " | Matricula: " + a.getMatricula());
        }
    }

    public void listarProfessores() {
        System.out.println("\n--- Lista de Professores ---");
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }
        for (Professor p : professores) {
            System.out.println("Nome: " + p.getNome() + " | Matricula: " + p.getMatricula());
        }
    }

    public void listarTurmas() {
        System.out.println("\n--- Lista de Turmas ---");
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }
        for (Turma t : turmas) {
            System.out.println("Turma: " + t.getCodigoTurma() +
                    " | Disciplina: " + t.getNomeDisciplina() +
                    " | Professor: " + t.getResponsavel().getNome());
        }
    }

    public void listarAlunosDaTurma(String codTurma) {
        Turma turma = buscarTurma(codTurma);
        if (turma == null) {
            System.out.println("Turma não encontrada!");
            return;
        }

        System.out.println("\n--- Alunos da Turma " + codTurma + " ---");
        if (turma.getAlunos().isEmpty()) {
            System.out.println("Nenhum aluno matriculado.");
            return;
        }

        for (Aluno a : turma.getAlunos()) {
            System.out.println("- " + a.getNome() + " | Matricula: " + a.getMatricula());
        }
    }
}
