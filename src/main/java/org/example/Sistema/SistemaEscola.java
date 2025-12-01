package org.example.Sistema;

import org.example.Admin;
import org.example.Aluno;
import org.example.Display.MenuAdmin;
import org.example.Display.MenuAluno;
import org.example.Display.MenuPrincipal;
import org.example.Display.MenuProfessor;
import org.example.Pessoa;
import org.example.Professor;

public class SistemaEscola {

    private CadastroPessoas cadastro = new CadastroPessoas();

    private MenuAluno menuAluno;
    private MenuProfessor menuProfessor;
    private MenuAdmin menuAdmin;
    private MenuPrincipal menuPrincipal;

    public SistemaEscola() {

        // Criar o Admin padrão (SEM cadastrar alunos/professores aqui)
        Admin adm = new Admin(
                "Administrador",
                111111111,
                30,
                true,
                'M',
                1
        );

        Professor professor = new Professor(
                "Ricarda",
                100, 19,
                true,
                'F',
                2
        );

        Aluno aluno = new Aluno(
                "Igor", 200, 19, true, 'M', 10
        );
        aluno.gerarBoleto(2400, "Oi", "Teste");
        adm.definirNovaSenha("admin");
        cadastro.addAdmin(adm);
        cadastro.addProfessor(professor);
        cadastro.addAluno(aluno);
        aluno.definirNovaSenha("1");
        professor.definirNovaSenha("123");

        // Instanciar menus
        this.menuAluno = new MenuAluno(this);
        this.menuProfessor = new MenuProfessor(this);
        this.menuAdmin = new MenuAdmin(this);
        this.menuPrincipal = new MenuPrincipal(this);
    }

    // Getters
    public MenuAluno getMenuAluno() { return menuAluno; }
    public MenuProfessor getMenuProfessor() { return menuProfessor; }
    public MenuAdmin getMenuAdmin() { return menuAdmin; }
    public MenuPrincipal getMenuPrincipal() { return menuPrincipal; }

    public CadastroPessoas getCadastro() {
        return cadastro;
    }

    public Pessoa login(int matricula, String senha) {
        Pessoa p = cadastro.buscarPorMatricula(matricula);
        if (p != null && p.verificarSenha(senha)) {
            return p;
        }
        return null;
    }

    public void iniciar() {
        menuPrincipal.mostrar();
    }
}

