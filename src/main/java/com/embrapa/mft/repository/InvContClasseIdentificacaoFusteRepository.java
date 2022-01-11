package com.embrapa.mft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.embrapa.mft.model.InvContClasseIdentificacaoFuste;
import com.embrapa.mft.repository.consultas.InvContClasseIdentificacaoFusteQuery;

public interface InvContClasseIdentificacaoFusteRepository extends JpaRepository<InvContClasseIdentificacaoFuste, Long> , InvContClasseIdentificacaoFusteQuery{

}
