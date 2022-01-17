package com.embrapa.mft.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.embrapa.mft.model.InvContFormaCopa;
import com.embrapa.mft.repository.InvContFormaCopaRepository;

@Service
public class InvContFormaCopaService {

	@Autowired
	private InvContFormaCopaRepository invContFormaCopaRepository;
 
 
	public InvContFormaCopa atualizar(Long cdFormaCopa, InvContFormaCopa invContFormaCopa) {
		 InvContFormaCopa invContFormaCopaSalva = buscarFamiliaPeloCodigo(cdFormaCopa);
		  BeanUtils.copyProperties(invContFormaCopa, invContFormaCopaSalva, "codigo");
		   return invContFormaCopaRepository.save(invContFormaCopaSalva);
	}
 
	public InvContFormaCopa buscarFamiliaPeloCodigo(Long cdFormaCopa) {
		InvContFormaCopa invContFormaCopaSalva = invContFormaCopaRepository.findOne(cdFormaCopa);
		if(invContFormaCopaSalva == null) {
			  throw new EmptyResultDataAccessException(1);
		}
	  
		return invContFormaCopaSalva;
	}
	
}
