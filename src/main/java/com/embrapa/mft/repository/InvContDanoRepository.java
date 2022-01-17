package com.embrapa.mft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.embrapa.mft.model.InvContDano;
import com.embrapa.mft.repository.consultas.InvContDanoRepositoryQuery;

public interface InvContDanoRepository extends JpaRepository<InvContDano, Long>, InvContDanoRepositoryQuery{
	
	

}
