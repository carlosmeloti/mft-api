package com.embrapa.mft.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.embrapa.mft.model.InvContCipo;
import com.embrapa.mft.repository.InvContCipoRepository;

@Service
public class InvContCipoService {

	@Autowired
	private InvContCipoRepository invContCipoRepository;
 
 
	public InvContCipo atualizar(Long cdCipo, InvContCipo invContCipo) {
		 InvContCipo invContCipoSalva = buscarFamiliaPeloCodigo(cdCipo);
		  BeanUtils.copyProperties(invContCipo, invContCipoSalva, "codigo");
		   return invContCipoRepository.save(invContCipoSalva);
	}
 
	public InvContCipo buscarFamiliaPeloCodigo(Long cdCipo) {
		InvContCipo invContCipoSalva = invContCipoRepository.findOne(cdCipo);
		if(invContCipoSalva == null) {
			  throw new EmptyResultDataAccessException(1);
		}
	  
		return invContCipoSalva;
	}
	
}
