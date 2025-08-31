package br.com.areaverde.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AreaVerde {
    private int id;
    private String nome;
    private Localizacao localizacao;
    private String tipoVegetacao;
    private String horariosFuncionamento;
    private List<String> atividadesDisponiveis = new ArrayList<>();
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public double calcularMediaAvaliacoes() {
        if (avaliacoes == null || avaliacoes.isEmpty()) {
            return 0.0;
        }
        double soma = 0.0;
        for (Avaliacao av : avaliacoes) {
            soma += av.calcularMedia();
        }
        return soma / avaliacoes.size();
    }
}