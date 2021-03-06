package com.embrapa.mft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.embrapa.mft.model.CadEmpresa;
import com.embrapa.mft.model.InvContIluminacao;

public interface InvContIluminacaoRepository extends JpaRepository<InvContIluminacao, Long> {
	
	public Page<InvContIluminacao> findByCdEmpresa(CadEmpresa cdEmpresa, Pageable pageable);

}
