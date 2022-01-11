package com.embrapa.mft.repository.consultas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.embrapa.mft.model.InvContClasseIdentificacaoFuste;
import com.embrapa.mft.repository.filter.InvContClasseIdentificacaoFusteFilter;

public interface InvContClasseIdentificacaoFusteQuery {
	
	public Page<InvContClasseIdentificacaoFuste> filtrar(InvContClasseIdentificacaoFusteFilter InvContClasseIdentificacaoFusteFilter, Pageable pageable);

}
