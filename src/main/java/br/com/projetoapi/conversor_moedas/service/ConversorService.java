package br.com.projetoapi.conversor_moedas.service;

import br.com.projetoapi.conversor_moedas.dto.CotacaoResponseDTO;
import br.com.projetoapi.conversor_moedas.dto.ConversaoResultadoDTO;
import br.com.projetoapi.conversor_moedas.entity.HistoricoConversao;
import br.com.projetoapi.conversor_moedas.repository.HistoricoConversaoRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ConversorService {

    private final HistoricoConversaoRepository repository;
    private final RestClient restClient;

    // Inicialização obrigatória do atributo final no construtor
    public ConversorService(HistoricoConversaoRepository repository) {
        this.repository = repository;
        this.restClient = RestClient.create();
    }

    public ConversaoResultadoDTO converter(String de, String para, BigDecimal valor) {
        String par = de.toUpperCase() + "-" + para.toUpperCase();
        String url = "https://economia.awesomeapi.com.br/json/last/" + par;

        Map<String, CotacaoResponseDTO> response = restClient.get()
                .uri(url)
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, CotacaoResponseDTO>>() {});

        if (response == null || !response.containsKey(de.toUpperCase() + para.toUpperCase())) {
            throw new IllegalArgumentException("Não foi possível obter a cotação para o par: " + par);
        }

        CotacaoResponseDTO cotacao = response.get(de.toUpperCase() + para.toUpperCase());
        BigDecimal taxa = cotacao.bid();
        BigDecimal valorConvertido = valor.multiply(taxa);

        HistoricoConversao historico = new HistoricoConversao();
        historico.setMoedaOrigem(de.toUpperCase());
        historico.setMoedaDestino(para.toUpperCase());
        historico.setValorOriginal(valor);
        historico.setTaxa(taxa);
        historico.setValorConvertido(valorConvertido);
        historico.setDataHora(LocalDateTime.now());

        // Atualizado para usar a classe HistoricoConversaoEntity
        HistoricoConversao salvo = repository.save(historico);

        return new ConversaoResultadoDTO(
                salvo.getMoedaOrigem(),
                salvo.getMoedaDestino(),
                salvo.getValorOriginal(),
                salvo.getTaxa(),
                salvo.getValorConvertido(),
                salvo.getDataHora()
        );
    }
}