package com.embrapa.mft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.embrapa.mft.model.CadEmpresa;
import com.embrapa.mft.model.InvContPodridao;

public interface InvContPodridaoRepository extends JpaRepository<InvContPodridao, Long>{
	
	public Page<InvContPodridao> findByCdEmpresa(CadEmpresa cdEmpresa, Pageable pageable);

}
