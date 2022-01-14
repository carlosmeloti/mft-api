package com.embrapa.mft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.embrapa.mft.model.InvContCIF;
import com.embrapa.mft.repository.consultas.InvContCIFRepositoryQuery;

public interface InvContCIFRepository extends JpaRepository<InvContCIF, Long>, InvContCIFRepositoryQuery{

}
