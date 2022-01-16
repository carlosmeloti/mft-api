package com.embrapa.mft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.embrapa.mft.model.InvContCIFMedicoesAnt;
import com.embrapa.mft.repository.consultas.InvContCIFMedicoesAntRepositoryQuery;

public interface InvContCIFMedicoesAntRepository extends JpaRepository<InvContCIFMedicoesAnt, Long>, InvContCIFMedicoesAntRepositoryQuery{

	
	
}
