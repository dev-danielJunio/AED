package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Pessoa {
    private String nome;
    private long CPF;
    private int idade;
    private String sal;
    private String senhaHash;
    private boolean criveis;
    private char sexo;
    private int matricula;

    public Pessoa(String nome, long CPF, int idade, boolean criveis, char sexo, int matricula) {
        this.nome = nome;
        this.CPF = CPF;
        this.idade = idade;
        this.criveis = criveis;
        this.sexo = sexo;
        this.matricula = matricula;
    }

    public void definirNovaSenha(String senha) {
        try {
            this.sal = gerarSalAleatorio();

            this.senhaHash = gerarHash(senha, this.sal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verificarSenha(String senhaEntrada) {
        try {
            String hashCalculado = gerarHash(senhaEntrada, this.sal);

            return this.senhaHash.equals(hashCalculado);

        } catch (Exception e) {
            return false;
        }
    }

    private String gerarSalAleatorio() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private String gerarHash(String senha, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        String mistura = senha + salt;

        byte[] hashBytes = md.digest(mistura.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public long getCPF() {
        return CPF;
    }

    public int getIdade() {
        return idade;
    }

    public char getSexo() {
        return sexo;
    }

    public boolean isCriveis() {
        return criveis;
    }
}