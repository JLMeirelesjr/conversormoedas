package br.com.projetoapi.conversor_moedas.controller;

import br.com.projetoapi.conversor_moedas.dto.ConversaoResultadoDTO;
import br.com.projetoapi.conversor_moedas.entity.HistoricoConversao;
import br.com.projetoapi.conversor_moedas.repository .HistoricoConversaoRepository;
import br.com.projetoapi.conversor_moedas.service.ConversorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cambio")
public class ConversorController {

    private final ConversorService conversorService;
    private final HistoricoConversaoRepository historicoRepository;

    public ConversorController(ConversorService conversorService, HistoricoConversaoRepository historicoRepository) {
        this.conversorService = conversorService;
        this.historicoRepository = historicoRepository;
    }

    @GetMapping("/converter")
    public ResponseEntity<ConversaoResultadoDTO> converter(
            @RequestParam String de,
            @RequestParam String para,
            @RequestParam BigDecimal valor) {

        ConversaoResultadoDTO resultado = conversorService.converter(de, para, valor);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/historico")
    public ResponseEntity<List<HistoricoConversao>> listarHistorico() {
        return ResponseEntity.ok(historicoRepository.findAll());
    }
}