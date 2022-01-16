package com.embrapa.mft.repository.consultas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.embrapa.mft.model.CadEmpresa;
import com.embrapa.mft.model.InvContCIF;
import com.embrapa.mft.model.InvContCIFMedicoesAnt;


public interface InvContCIFMedicoesAntRepositoryQuery {

	public Page<InvContCIFMedicoesAnt> findByCdEmpresaAndCdCIFAtual(CadEmpresa cdEmpresa, InvContCIF cdCIFAtual, Pageable pageable);
	
}
