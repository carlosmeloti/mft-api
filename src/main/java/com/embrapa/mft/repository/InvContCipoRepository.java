package com.embrapa.mft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.embrapa.mft.model.CadEmpresa;
import com.embrapa.mft.model.InvContCipo;

public interface InvContCipoRepository extends JpaRepository<InvContCipo, Long>{
	
	public Page<InvContCipo> findByCdEmpresa(CadEmpresa cdEmpresa, Pageable pageable);

}
