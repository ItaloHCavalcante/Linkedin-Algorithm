package br.com.unipe;

import java.util.List;

public class LinkedInApp {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(false, true);

        grafo.adicionaVertices(
                "Ana",
                "Bruno",
                "Carlos",
                "Daniela",
                "Eduardo",
                "Fernanda",
                "Gabriel",
                "Hugo",
                "Igor",
                "Juliana"
        );

        grafo.addAresta("Ana", "Bruno", 1);
        grafo.addAresta("Ana", "Carlos", 2);
        grafo.addAresta("Ana", "Daniela", 8);
        grafo.addAresta("Bruno", "Eduardo", 1);
        grafo.addAresta("Carlos", "Eduardo", 1);
        grafo.addAresta("Daniela", "Fernanda", 5);
        grafo.addAresta("Eduardo", "Fernanda", 1);
        grafo.addAresta("Gabriel", "Hugo", 1);
        grafo.addAresta("Igor", "Juliana", 1);

        LinkedInAnalyzer analyzer = new LinkedInAnalyzer(grafo);

        exibirSugestoes(analyzer, "Ana");
        exibirGrauSeparacao(analyzer, "Ana", "Fernanda");
        exibirMelhorAfinidade(analyzer, "Ana", "Fernanda");
        exibirComponentesConexos(analyzer);
    }

    private static void exibirSugestoes(LinkedInAnalyzer analyzer, String usuario) {
        System.out.println("========== Sugestoes ==========");
        System.out.println();
        System.out.println("Sugestoes para " + usuario);

        List<SugestaoConexao> sugestoes = analyzer.sugerirConexoes(usuario);

        for (SugestaoConexao sugestao : sugestoes) {
            System.out.println(sugestao.getNome() + " - "
                    + sugestao.getAmigosEmComum() + " "
                    + textoAmigosEmComum(sugestao.getAmigosEmComum()));
        }

        System.out.println();
    }

    private static void exibirGrauSeparacao(LinkedInAnalyzer analyzer, String origem, String destino) {
        System.out.println("========== Grau de Separacao ==========");
        System.out.println();
        System.out.println(origem + " -> " + destino);

        int grau = analyzer.calcularGrauSeparacao(origem, destino);

        if (grau == -1) {
            System.out.println("Nao existe caminho");
        } else {
            System.out.println(grau + " passos");
        }

        System.out.println();
    }

    private static void exibirMelhorAfinidade(LinkedInAnalyzer analyzer, String origem, String destino) {
        System.out.println("========== Melhor Afinidade ==========");
        System.out.println();

        ResultadoCaminho resultado = analyzer.buscarMelhorRotaAfinidade(origem, destino);

        if (resultado.getCaminho().isEmpty()) {
            System.out.println("Nao existe caminho entre " + origem + " e " + destino);
        } else {
            System.out.println(String.join(" -> ", resultado.getCaminho()));
            System.out.println();
            System.out.println("Custo total: " + resultado.getCusto());
        }

        System.out.println();
    }

    private static void exibirComponentesConexos(LinkedInAnalyzer analyzer) {
        System.out.println("========== Componentes Conexos ==========");
        System.out.println();

        List<List<String>> componentes = analyzer.listarComponentesConexos();

        for (int i = 0; i < componentes.size(); i++) {
            System.out.println("Grupo " + (i + 1));
            System.out.println();

            for (String pessoa : componentes.get(i)) {
                System.out.println(pessoa);
            }

            System.out.println();
        }
    }

    private static String textoAmigosEmComum(int quantidade) {
        return quantidade == 1 ? "amigo em comum" : "amigos em comum";
    }
}
