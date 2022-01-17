package com.embrapa.mft.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.embrapa.mft.event.RecursoCriadoEvent;
import com.embrapa.mft.model.CadEmpresa;
import com.embrapa.mft.model.InvContCIF;
import com.embrapa.mft.model.InvContPodridao;
import com.embrapa.mft.repository.InvContPodridaoRepository;
import com.embrapa.mft.repository.filter.InvContPodridaoFilter;
import com.embrapa.mft.service.InvContPodridaoService;

@RestController
@RequestMapping("/prodridao")
public class InvContPodridaoResource {

	@Autowired
	private InvContPodridaoRepository invContPodridaoRepository; 
	
	@Autowired
	private InvContPodridaoService invContPodridaoService;
	
	@Autowired 
	private ApplicationEventPublisher eventPublisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESPECIE') and #oauth2.hasScope('read')")
	public Page<InvContPodridao> pesquisar(InvContPodridaoFilter invContPodridaoFilterFilter, Pageable pageable){ 
    	CadEmpresa empresa = new CadEmpresa();    	
    	empresa.setCdEmpresa(invContPodridaoFilterFilter.getCdEmpresa());	
		return invContPodridaoRepository.findByCdEmpresa(empresa, pageable); 
    }
	
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
    public ResponseEntity<InvContPodridao> criar(@RequestBody InvContPodridao invContPodridao, HttpServletResponse response){
    	InvContPodridao invContPodridaoSalva = invContPodridaoRepository.save(invContPodridao);
    	eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, invContPodridaoSalva.getCdPodridao()));
	    	 
	    return ResponseEntity.status(HttpStatus.CREATED).body(invContPodridaoSalva);
	}
	    
	    
	@GetMapping("/{cdPodridao}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FAMILIA') and #oauth2.hasScope('read')")
	public ResponseEntity<InvContPodridao> buscarPorCodigo(@PathVariable Long cdPodridao){
		InvContPodridao invContPodridao= invContPodridaoRepository.findOne(cdPodridao);
	    return invContPodridao != null ? ResponseEntity.ok(invContPodridao) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cdPodridao}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_FAMILIA') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long cdPodridao) {
		invContPodridaoRepository.delete(cdPodridao);
	}

    @PutMapping("/{cdPodridao}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
	public ResponseEntity<InvContPodridao> atualizar(@PathVariable Long cdPodridao, @Valid @RequestBody InvContPodridao invContPodridao){
	   	InvContPodridao invContPodridaoSalva = invContPodridaoService.atualizar(cdPodridao, invContPodridao);
	    return ResponseEntity.ok(invContPodridaoSalva);
	}
	
}
