package br.com.areaverde.api.repositories;

import br.com.areaverde.api.models.Localizacao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class LocalizacaoRepository {
    private final List<Localizacao> LOCALIZACOES = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public Localizacao salvar(Localizacao localizacao) {
        localizacao.setId(idCounter.incrementAndGet());
        LOCALIZACOES.add(localizacao);
        return localizacao;
    }
}