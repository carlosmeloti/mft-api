package com.embrapa.mft.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.embrapa.mft.model.InvContClasseIdentificacaoFuste;
import com.embrapa.mft.repository.InvContClasseIdentificacaoFusteRepository;


@Service
public class InvContClasseIdentificacaoFusteService {
	
	@Autowired
	private InvContClasseIdentificacaoFusteRepository invContClasseIdentificacaoFusteRepository;
 
 
	public InvContClasseIdentificacaoFuste atualizar(Long cdFamilia, InvContClasseIdentificacaoFuste invContClasseIdentificacaoFuste) {
		 InvContClasseIdentificacaoFuste invContClasseIdentificacaoFusteSalva = buscarFamiliaPeloCodigo(cdFamilia);
		  BeanUtils.copyProperties(invContClasseIdentificacaoFuste, invContClasseIdentificacaoFusteSalva, "codigo");
		   return invContClasseIdentificacaoFusteRepository.save(invContClasseIdentificacaoFusteSalva);
	}
 
	public InvContClasseIdentificacaoFuste buscarFamiliaPeloCodigo(Long cdFamilia) {
		InvContClasseIdentificacaoFuste invContClasseIdentificacaoFusteSalva = invContClasseIdentificacaoFusteRepository.findOne(cdFamilia);
		if(invContClasseIdentificacaoFusteSalva == null) {
			  throw new EmptyResultDataAccessException(1);
		}
	  
		return invContClasseIdentificacaoFusteSalva;
	}

}
