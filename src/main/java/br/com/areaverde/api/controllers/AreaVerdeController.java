package br.com.areaverde.api.controllers;

import br.com.areaverde.api.models.AreaVerde;
import br.com.areaverde.api.models.Avaliacao;
import br.com.areaverde.api.repositories.AreaVerdeRepository;
import br.com.areaverde.api.repositories.AvaliacaoRepository;
import br.com.areaverde.api.repositories.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/areasverdes")
public class AreaVerdeController {

    @Autowired
    private AreaVerdeRepository areaVerdeRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @GetMapping
    public List<AreaVerde> listarAreas() {
        return areaVerdeRepository.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaVerde> verDetalhesArea(@PathVariable int id) {
        AreaVerde area = areaVerdeRepository.buscarPorId(id);
        if (area != null) {
            return ResponseEntity.ok(area);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AreaVerde> cadastrarAreaVerde(@RequestBody AreaVerde novaArea) {
        // Salva a localização primeiro
        if (novaArea.getLocalizacao() != null) {
            localizacaoRepository.salvar(novaArea.getLocalizacao());
        }

        // Salva a área verde
        AreaVerde areaSalva = areaVerdeRepository.salvar(novaArea);

        // Associa os IDs
        if (novaArea.getLocalizacao() != null) {
            novaArea.getLocalizacao().setIdAreaVerde(areaSalva.getId());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(areaSalva);
    }

    @PostMapping("/{id}/avaliacoes")
    public ResponseEntity<String> avaliarArea(@PathVariable int id, @RequestBody Avaliacao novaAvaliacao) {
        AreaVerde area = areaVerdeRepository.buscarPorId(id);
        if (area == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Área Verde não encontrada!");
        }

        novaAvaliacao.setIdAreaVerde(id);
        avaliacaoRepository.salvar(novaAvaliacao); // Salva a avaliação no repositório dela
        area.getAvaliacoes().add(novaAvaliacao); // Adiciona na lista da área verde

        return ResponseEntity.ok("Avaliação adicionada com sucesso!");
    }
}