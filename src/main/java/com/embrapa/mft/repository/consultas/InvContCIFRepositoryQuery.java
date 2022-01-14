package com.embrapa.mft.repository.consultas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.embrapa.mft.model.InvContCIF;
import com.embrapa.mft.repository.filter.InvContCIFFilter;

public interface InvContCIFRepositoryQuery {

	
	public Page<InvContCIF> filtrar(InvContCIFFilter invContCIFFilter, Pageable pageable);

	
}
