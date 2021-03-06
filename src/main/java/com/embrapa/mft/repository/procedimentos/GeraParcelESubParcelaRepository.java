package com.embrapa.mft.repository.procedimentos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.embrapa.mft.model.fnc.GeraParcelESubParcela;

public interface GeraParcelESubParcelaRepository extends JpaRepository<GeraParcelESubParcela, Long>{

	@Query(value = "select count(*) from d21_parcela  where  "
			+ "d21_cdempresa = :cdempresa and d21_cdarea = "
			+ ":cdarea and d21_cdparcela = :contpar", nativeQuery = true)
	public Integer qtdexiste(@Param("cdempresa") Long cdEmpresa, @Param("cdarea") 
	       Long cdArea, @Param("contpar") int contapar);
	
	@Query(value = "select max(d21_cdparcela) from d21_parcela", nativeQuery = true)
	public Long maxCdParcela();
	
	
	@Query(value = "select count(*) from d29_subparcela  where  d29_cdempresa = :cdempresa and d29_cdarea = "
			+ ":cdarea and d29_cdparcela = :cdparcela and d29_cdsubparcela = :cdsubparcela", nativeQuery = true)
	public Integer qtdexisteSub(@Param("cdempresa") Long cdEmpresa, @Param("cdarea") Long cdArea, 
			@Param("cdparcela") Integer cdparcela, @Param("cdsubparcela") Integer cdsubparcela);
	
	
}
