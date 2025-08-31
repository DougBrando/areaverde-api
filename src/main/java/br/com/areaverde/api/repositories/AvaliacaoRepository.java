package br.com.areaverde.api.repositories;

import br.com.areaverde.api.models.Avaliacao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AvaliacaoRepository {
    private final List<Avaliacao> AVALIACOES = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public Avaliacao salvar(Avaliacao avaliacao) {
        avaliacao.setId(idCounter.incrementAndGet());
        AVALIACOES.add(avaliacao);
        return avaliacao;
    }

    public List<Avaliacao> listarTodas() {
        return AVALIACOES;
    }
}