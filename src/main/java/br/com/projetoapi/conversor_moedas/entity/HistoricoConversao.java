package br.com.projetoapi.conversor_moedas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoConversao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String moedaOrigem;
    private String moedaDestino;
    private BigDecimal valorOriginal;
    private BigDecimal taxa;
    private BigDecimal valorConvertido;
    private LocalDateTime dataHora;

}
