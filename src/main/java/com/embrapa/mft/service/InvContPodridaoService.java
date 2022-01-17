package com.embrapa.mft.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.embrapa.mft.model.InvContPodridao;
import com.embrapa.mft.repository.InvContPodridaoRepository;

@Service
public class InvContPodridaoService {

	@Autowired
	private InvContPodridaoRepository invContPodridaoRepository;
 
 
	public InvContPodridao atualizar(Long cdPodridao, InvContPodridao invContPodridao) {
		 InvContPodridao invContPodridaoSalva = buscarFamiliaPeloCodigo(cdPodridao);
		  BeanUtils.copyProperties(invContPodridao, invContPodridaoSalva, "codigo");
		   return invContPodridaoRepository.save(invContPodridaoSalva);
	}
 
	public InvContPodridao buscarFamiliaPeloCodigo(Long cdPodridao) {
		InvContPodridao invContPodridaoSalva = invContPodridaoRepository.findOne(cdPodridao);
		if(invContPodridaoSalva == null) {
			  throw new EmptyResultDataAccessException(1);
		}
	  
		return invContPodridaoSalva;
	}
	
}
