package com.embrapa.mft.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.embrapa.mft.model.InvContDano;
import com.embrapa.mft.repository.InvContDanoRepository;

@Service
public class InvContDanoService {

	@Autowired
	private InvContDanoRepository invContDanoRepository;
 
 
	public InvContDano atualizar(Long cdDano, InvContDano invContDano) {
		 InvContDano invContDanoSalva = buscarFamiliaPeloCodigo(cdDano);
		  BeanUtils.copyProperties(invContDano, invContDanoSalva, "codigo");
		   return invContDanoRepository.save(invContDanoSalva);
	}
 
	public InvContDano buscarFamiliaPeloCodigo(Long cdDano) {
		InvContDano invContDanoSalva = invContDanoRepository.findOne(cdDano);
		if(invContDanoSalva == null) {
			  throw new EmptyResultDataAccessException(1);
		}
	  
		return invContDanoSalva;
	}
	
}
