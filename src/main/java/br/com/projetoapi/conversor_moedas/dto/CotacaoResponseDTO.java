package br.com.projetoapi.conversor_moedas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record CotacaoResponseDTO(
        String code,
        String codein,
        BigDecimal bid,
        @JsonProperty("create_date") String createDate
) {}