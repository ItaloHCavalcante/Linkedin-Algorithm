package br.com.unipe;

import java.util.List;

public class ResultadoCaminho {
    private List<String> caminho;
    private int custo;

    public ResultadoCaminho(List<String> caminho, int custo) {
        this.caminho = caminho;
        this.custo = custo;
    }

    public List<String> getCaminho() {
        return caminho;
    }

    public void setCaminho(List<String> caminho) {
        this.caminho = caminho;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }
}
