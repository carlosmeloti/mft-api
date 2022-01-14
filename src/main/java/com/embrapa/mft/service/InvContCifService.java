package com.embrapa.mft.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.embrapa.mft.model.InvContCIF;
import com.embrapa.mft.repository.InvContCIFRepository;

@Service
public class InvContCIFService {
	
	@Autowired
	private InvContCIFRepository invContCIFRepository;
 
 
	public InvContCIF atualizar(Long cdFamilia, InvContCIF invContClasseIdentificacaoFuste) {
		 InvContCIF invContClasseIdentificacaoFusteSalva = buscarFamiliaPeloCodigo(cdFamilia);
		  BeanUtils.copyProperties(invContClasseIdentificacaoFuste, invContClasseIdentificacaoFusteSalva, "codigo");
		   return invContCIFRepository.save(invContClasseIdentificacaoFusteSalva);
	}
 
	public InvContCIF buscarFamiliaPeloCodigo(Long cdFamilia) {
		InvContCIF invContClasseIdentificacaoFusteSalva = invContCIFRepository.findOne(cdFamilia);
		if(invContClasseIdentificacaoFusteSalva == null) {
			  throw new EmptyResultDataAccessException(1);
		}
	  
		return invContClasseIdentificacaoFusteSalva;
	}

}
