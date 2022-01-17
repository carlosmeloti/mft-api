package com.embrapa.mft.repository.consultas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.embrapa.mft.model.CadEmpresa;
import com.embrapa.mft.model.InvContDano;

public interface InvContDanoRepositoryQuery {
	
	public Page<InvContDano> findByCdEmpresa(CadEmpresa cdEmpresa, Pageable pageable);

}
