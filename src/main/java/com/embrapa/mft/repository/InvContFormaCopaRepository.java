package com.embrapa.mft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.embrapa.mft.model.CadEmpresa;
import com.embrapa.mft.model.InvContFormaCopa;

public interface InvContFormaCopaRepository extends JpaRepository<InvContFormaCopa, Long>{

	public Page<InvContFormaCopa> findByCdEmpresa(CadEmpresa cdEmpresa, Pageable pageable);

}
