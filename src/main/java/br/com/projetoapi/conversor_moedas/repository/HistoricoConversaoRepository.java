package br.com.projetoapi.conversor_moedas.repository;

import br.com.projetoapi.conversor_moedas.entity.HistoricoConversao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoConversaoRepository extends JpaRepository<HistoricoConversao, Long> {
}