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
import com.embrapa.mft.model.InvContCipo;
import com.embrapa.mft.repository.InvContCipoRepository;
import com.embrapa.mft.repository.filter.InvContCipoFilter;
import com.embrapa.mft.service.InvContCipoService;

@RestController
@RequestMapping("/cipo")
public class InvContCipoResource {

	@Autowired
	private InvContCipoRepository invContCipoRepository; 
	
	@Autowired
	private InvContCipoService invContCipoService;
	
	@Autowired 
	private ApplicationEventPublisher eventPublisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESPECIE') and #oauth2.hasScope('read')")
	public Page<InvContCipo> pesquisar(InvContCipoFilter invContCipoFilterFilter, Pageable pageable){ 
    	CadEmpresa empresa = new CadEmpresa();    	
    	empresa.setCdEmpresa(invContCipoFilterFilter.getCdEmpresa());	
		return invContCipoRepository.findByCdEmpresa(empresa, pageable); 
    }
	
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
    public ResponseEntity<InvContCipo> criar(@RequestBody InvContCipo invContCipo, HttpServletResponse response){
    	InvContCipo invContCipoSalva = invContCipoRepository.save(invContCipo);
    	eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, invContCipoSalva.getCdCipo()));
	    	 
	    return ResponseEntity.status(HttpStatus.CREATED).body(invContCipoSalva);
	}
	    
	    
	@GetMapping("/{cdCipo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FAMILIA') and #oauth2.hasScope('read')")
	public ResponseEntity<InvContCipo> buscarPorCodigo(@PathVariable Long cdCipo){
		InvContCipo invContCipo= invContCipoRepository.findOne(cdCipo);
	    return invContCipo != null ? ResponseEntity.ok(invContCipo) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cdCipo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_FAMILIA') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long cdCipo) {
		invContCipoRepository.delete(cdCipo);
	}

    @PutMapping("/{cdCipo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
	public ResponseEntity<InvContCipo> atualizar(@PathVariable Long cdCipo, @Valid @RequestBody InvContCipo invContCipo){
	   	InvContCipo invContCipoSalva = invContCipoService.atualizar(cdCipo, invContCipo);
	    return ResponseEntity.ok(invContCipoSalva);
	}
	
}
