package br.com.projetoapi.conversor_moedas.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConversaoResultadoDTO(
        String moedaOrigem,
        String moedaDestino,
        BigDecimal valorOriginal,
        BigDecimal taxa,
        BigDecimal valorConvertido,
        LocalDateTime dataHora
) {}