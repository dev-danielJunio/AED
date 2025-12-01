package org.example;

public class Boleto implements IImprimivel {
    private double fatura;
    private String dataemissao;
    private String datavencimento;
    private Aluno pertecente;
    private boolean situacao;

    public Boleto(double fatura, String dataemissao, String datavencimento, Aluno pertecente) {
        this.dataemissao = dataemissao;
        this.datavencimento = datavencimento;
        this.pertecente = pertecente;
        situacao = false;
        if (pertecente.getBolsas() != null ) {
            this.fatura = fatura - ((fatura * pertecente.getBolsas().getDesconto())/100);
            return;
        }
        this.fatura = fatura;
    }

    @Override
    public void imprmir() {
        System.out.printf("Aluno: %s\nMatricula: %d\nFatura: %.2f\n", pertecente.getNome(), pertecente.getMatricula(), fatura);
        System.out.printf("Data de emissão: %s\nData de vencimento: %s\nStatus: ", dataemissao, datavencimento);
        if (situacao) {
            System.out.printf("Foi paga\n");
        } else {
            System.out.printf("Não foi paga\n");
        }
    }

    public void pagar(int valor) {
        if (fatura - valor > 0) {
            fatura = fatura - valor;
        } else {
            fatura = 0;
            situacao = true;
        }

    }

    public boolean isSituacao() {
        return situacao;
    }
}
