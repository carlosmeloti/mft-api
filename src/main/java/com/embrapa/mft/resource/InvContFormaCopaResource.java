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
import com.embrapa.mft.model.InvContFormaCopa;
import com.embrapa.mft.repository.InvContFormaCopaRepository;
import com.embrapa.mft.repository.filter.InvContFormaCopaFilter;
import com.embrapa.mft.service.InvContFormaCopaService;

@RestController
@RequestMapping("/formacopa")
public class InvContFormaCopaResource {

	@Autowired
	private InvContFormaCopaRepository invContFormaCopaRepository; 
	
	@Autowired
	private InvContFormaCopaService invContFormaCopaService;
	
	@Autowired 
	private ApplicationEventPublisher eventPublisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESPECIE') and #oauth2.hasScope('read')")
	public Page<InvContFormaCopa> pesquisar(InvContFormaCopaFilter invContFormaCopaFilterFilter, Pageable pageable){ 
    	CadEmpresa empresa = new CadEmpresa();    	
    	empresa.setCdEmpresa(invContFormaCopaFilterFilter.getCdEmpresa());	
		return invContFormaCopaRepository.findByCdEmpresa(empresa, pageable); 
    }
	
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
    public ResponseEntity<InvContFormaCopa> criar(@RequestBody InvContFormaCopa invContFormaCopa, HttpServletResponse response){
    	InvContFormaCopa invContFormaCopaSalva = invContFormaCopaRepository.save(invContFormaCopa);
    	eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, invContFormaCopaSalva.getCdFormaCopa()));
	    	 
	    return ResponseEntity.status(HttpStatus.CREATED).body(invContFormaCopaSalva);
	}
	    
	    
	@GetMapping("/{cdFormaCopa}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FAMILIA') and #oauth2.hasScope('read')")
	public ResponseEntity<InvContFormaCopa> buscarPorCodigo(@PathVariable Long cdFormaCopa){
		InvContFormaCopa invContFormaCopa= invContFormaCopaRepository.findOne(cdFormaCopa);
	    return invContFormaCopa != null ? ResponseEntity.ok(invContFormaCopa) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cdFormaCopa}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_FAMILIA') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long cdFormaCopa) {
		invContFormaCopaRepository.delete(cdFormaCopa);
	}

    @PutMapping("/{cdFormaCopa}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
	public ResponseEntity<InvContFormaCopa> atualizar(@PathVariable Long cdFormaCopa, @Valid @RequestBody InvContFormaCopa invContFormaCopa){
	   	InvContFormaCopa invContFormaCopaSalva = invContFormaCopaService.atualizar(cdFormaCopa, invContFormaCopa);
	    return ResponseEntity.ok(invContFormaCopaSalva);
	}
	
}
