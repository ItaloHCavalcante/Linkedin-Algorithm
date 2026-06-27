package br.com.unipe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LinkedInAnalyzer {
    private Grafo grafo;

    public LinkedInAnalyzer(Grafo grafo) {
        this.grafo = grafo;
    }

    public List<SugestaoConexao> sugerirConexoes(String usuario) {
        List<String> amigosDiretos = grafo.getNomesAdjacencias(usuario);
        Set<String> amigosDiretosSet = new HashSet<>(amigosDiretos);
        Map<String, Set<String>> amigosEmComum = new HashMap<>();

        for (String amigo : amigosDiretosSet) {
            List<String> amigosDoAmigo = grafo.getNomesAdjacencias(amigo);

            for (String candidato : amigosDoAmigo) {
                if (candidato.equalsIgnoreCase(usuario) || amigosDiretosSet.contains(candidato)) {
                    continue;
                }

                amigosEmComum
                        .computeIfAbsent(candidato, chave -> new HashSet<>())
                        .add(amigo);
            }
        }

        List<SugestaoConexao> sugestoes = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entrada : amigosEmComum.entrySet()) {
            sugestoes.add(new SugestaoConexao(entrada.getKey(), entrada.getValue().size()));
        }

        sugestoes.sort(Comparator.comparingInt(SugestaoConexao::getAmigosEmComum)
                .reversed()
                .thenComparing(SugestaoConexao::getNome));

        return sugestoes;
    }

    public int calcularGrauSeparacao(String origem, String destino) {
        return grafo.buscaEmLargura(origem, destino);
    }

    public ResultadoCaminho buscarMelhorRotaAfinidade(String origem, String destino) {
        return grafo.dijkstra(origem, destino);
    }

    public List<List<String>> listarComponentesConexos() {
        return grafo.componentesConexos();
    }
}
