package org.example;
import java.util.List;
import java.util.ArrayList;

public class Aluno extends Pessoa{
    private int creditos;
    private boolean pago;
    private List<DisciplinaCursada> HistoricoEscolar;
    private List<DisciplinaCursada> HistoricoAtual;
    private Turma[] turmas;
    private int i;
    private List<Boleto> fatura;
    private Bolsa bolsas;
    private Curso curso;

    public void consultarBoleto() { // Emissão de boletos
        System.out.printf("Consultando boletos não pagos");

        for (Boleto b : fatura) {
            if (b.isSituacao()) {
                b.imprmir();
            }
        }
    }

    public void consultarSemestre() { // Notas e frequências
        System.out.printf("Consultando o histórico do semestre atual!\n");
        for (DisciplinaCursada b : HistoricoAtual) {
            System.out.print(b.toString());
        }
    }

    public void consultarHistorico() { // Histórico escolar
        System.out.printf("Consultando o histórico escolar\n");
        for (DisciplinaCursada b : HistoricoEscolar) {
            System.out.print(b.Historico());
        }
    }

    public void matricularNaDisciplina(DisciplinaCursada disciplina) { // Matricula/Inclusão
        this.HistoricoAtual.add(disciplina);
    }

    public DisciplinaCursada buscarDisciplina(String codigo) {
        for (DisciplinaCursada b : HistoricoAtual) {
            if (b.getCodigodisciplina() == codigo) {
                return b;
            }
        }
        return null;
    }

    public void consultarHorarios() { // Meus horarios
        for (DisciplinaCursada b: HistoricoAtual) {
            b.meuHorario();
        }
    }

    public Aluno(String nome, long CPF, int idade, boolean criveis, char sexo, int matricula) {
        super(nome, CPF, idade, criveis, sexo, matricula);
        this.HistoricoAtual = new ArrayList<>();
        this.HistoricoEscolar = new ArrayList<>();
    }

    public Bolsa getBolsas() {
        return bolsas;
    }
}
