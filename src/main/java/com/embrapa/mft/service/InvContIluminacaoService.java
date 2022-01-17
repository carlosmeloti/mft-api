package com.embrapa.mft.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.embrapa.mft.model.InvContIluminacao;
import com.embrapa.mft.repository.InvContIluminacaoRepository;

@Service
public class InvContIluminacaoService {

	@Autowired
	private InvContIluminacaoRepository invContIluminacaoRepository;
 
 
	public InvContIluminacao atualizar(Long cdIluminacao, InvContIluminacao invContIluminacao) {
		 InvContIluminacao invContIluminacaoSalva = buscarFamiliaPeloCodigo(cdIluminacao);
		  BeanUtils.copyProperties(invContIluminacao, invContIluminacaoSalva, "codigo");
		   return invContIluminacaoRepository.save(invContIluminacaoSalva);
	}
 
	public InvContIluminacao buscarFamiliaPeloCodigo(Long cdIluminacao) {
		InvContIluminacao invContIluminacaoSalva = invContIluminacaoRepository.findOne(cdIluminacao);
		if(invContIluminacaoSalva == null) {
			  throw new EmptyResultDataAccessException(1);
		}
	  
		return invContIluminacaoSalva;
	}
	
}
