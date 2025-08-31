package br.com.areaverde.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
    private int id;
    private int quantidadeArvores;
    private int qualidadeAr;
    private int ausenciaPoluicaoSonora;
    private int coletaResiduos;
    private int facilidadeTraspPublico;
    private int idAreaVerde;

    public double calcularMedia() {
        int soma = quantidadeArvores + qualidadeAr + ausenciaPoluicaoSonora +
                coletaResiduos + facilidadeTraspPublico;
        return soma / 5.0;
    }
}