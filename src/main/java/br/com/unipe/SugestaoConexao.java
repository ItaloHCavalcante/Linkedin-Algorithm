package br.com.unipe;

public class SugestaoConexao {
    private String nome;
    private int amigosEmComum;

    public SugestaoConexao(String nome, int amigosEmComum) {
        this.nome = nome;
        this.amigosEmComum = amigosEmComum;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAmigosEmComum() {
        return amigosEmComum;
    }

    public void setAmigosEmComum(int amigosEmComum) {
        this.amigosEmComum = amigosEmComum;
    }
}
