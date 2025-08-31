package br.com.areaverde.api.repositories;

import br.com.areaverde.api.models.AreaVerde;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AreaVerdeRepository {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String DATABASE_FILE = "dados_areas_verdes.json";

    private final List<AreaVerde> AREAS_VERDES = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    @PostConstruct
    public void carregarDadosDoArquivo() {
        try {
            File database = new File(DATABASE_FILE);
            if (database.exists()) {
                List<AreaVerde> areasCarregadas = objectMapper.readValue(database, new TypeReference<List<AreaVerde>>() {});
                AREAS_VERDES.addAll(areasCarregadas);

                int maxId = areasCarregadas.stream()
                        .mapToInt(AreaVerde::getId)
                        .max()
                        .orElse(0);
                idCounter.set(maxId);
                System.out.println(">>> Dados carregados com sucesso do arquivo: " + DATABASE_FILE);
            }
        } catch (IOException e) {
            System.err.println("!!! Erro ao carregar dados do arquivo: " + e.getMessage());
        }
    }

    // NOVO: Método privado para salvar a lista inteira no arquivo
    private void salvarDadosEmArquivo() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(DATABASE_FILE), AREAS_VERDES);
        } catch (IOException e) {
            System.err.println("!!! Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    public AreaVerde salvar(AreaVerde areaVerde) {
        areaVerde.setId(idCounter.incrementAndGet());
        AREAS_VERDES.add(areaVerde);
        salvarDadosEmArquivo();
        return areaVerde;
    }

    public List<AreaVerde> listarTodos() {
        return AREAS_VERDES;
    }

    public AreaVerde buscarPorId(int id) {
        return AREAS_VERDES.stream()
                .filter(av -> av.getId() == id)
                .findFirst()
                .orElse(null);
    }
}