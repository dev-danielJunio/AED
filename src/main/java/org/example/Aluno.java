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
        System.out.printf("Consultando boletos não pagos\n");

        for (Boleto b : fatura) {
            if (!b.isSituacao()) {
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
            System.out.printf("Código | Nome | Período | Média | Créditos | Situação\n");
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

    public void consolidarTurma(String codigoTurma) {
        DisciplinaCursada disciplinaAlvo = null;

        for (DisciplinaCursada d : HistoricoAtual) {
            if (d.getTurma().equalsIgnoreCase(codigoTurma)) {
                disciplinaAlvo = d;
                break;
            }
        }

        if (disciplinaAlvo != null) {
            disciplinaAlvo.finalizarDisciplina();

            HistoricoEscolar.add(disciplinaAlvo);
            HistoricoAtual.remove(disciplinaAlvo);

        }
    }

    public void setBolsas(Bolsa bolsas) {
        this.bolsas = bolsas;
    }

    public void consultarHorarios() { // Meus horarios
        for (DisciplinaCursada b: HistoricoAtual) {
            b.meuHorario();
        }
    }

    public void removerDisciplinaDoHistorico(String codigoTurma) {
        HistoricoAtual.removeIf(d -> d.getTurma().equals(codigoTurma));
    }

    public int getCreditos() {
        return creditos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void gerarBoleto(double N, String data1, String data2) {
        Boleto boleto = new Boleto(N, data1, data2, this);
        fatura.add(boleto);
    }

    public void tirarBolsa() {
        this.bolsas = null;
    }

    public Aluno(String nome, long CPF, int idade, boolean criveis, char sexo, int matricula) {
        super(nome, CPF, idade, criveis, sexo, matricula);
        this.HistoricoAtual = new ArrayList<>();
        this.HistoricoEscolar = new ArrayList<>();
        this.fatura = new ArrayList<>();
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Bolsa getBolsas() {
        return bolsas;
    }
}
