package br.com.projetoapi.conversor_moedas.repository;

import br.com.projetoapi.conversor_moedas.entity.MoedaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoedaRepository extends JpaRepository<MoedaEntity, Long> {

    Optional<MoedaEntity> findByCodigo(String codigo);
}
