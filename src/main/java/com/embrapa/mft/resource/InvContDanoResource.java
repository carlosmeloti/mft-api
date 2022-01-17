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
import com.embrapa.mft.model.InvContDano;
import com.embrapa.mft.repository.InvContDanoRepository;
import com.embrapa.mft.repository.filter.InvContDanoFilter;
import com.embrapa.mft.service.InvContDanoService;

@RestController
@RequestMapping("/danos")
public class InvContDanoResource {

	@Autowired
	private InvContDanoRepository invContDanoRepository; 
	
	@Autowired
	private InvContDanoService invContDanoService;
	
	@Autowired 
	private ApplicationEventPublisher eventPublisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESPECIE') and #oauth2.hasScope('read')")
	public Page<InvContDano> pesquisar(InvContDanoFilter invContDanoFilterFilter, Pageable pageable){ 
    	CadEmpresa empresa = new CadEmpresa();    	
    	empresa.setCdEmpresa(invContDanoFilterFilter.getCdEmpresa());	
		return invContDanoRepository.findByCdEmpresa(empresa, pageable); 
    }
	
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
    public ResponseEntity<InvContDano> criar(@RequestBody InvContDano invContDano, HttpServletResponse response){
    	InvContDano invContDanoSalva = invContDanoRepository.save(invContDano);
    	eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, invContDanoSalva.getCdDano()));
	    	 
	    return ResponseEntity.status(HttpStatus.CREATED).body(invContDanoSalva);
	}
	    
	    
	@GetMapping("/{cdDano}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FAMILIA') and #oauth2.hasScope('read')")
	public ResponseEntity<InvContDano> buscarPorCodigo(@PathVariable Long cdDano){
		InvContDano invContDano= invContDanoRepository.findOne(cdDano);
	    return invContDano != null ? ResponseEntity.ok(invContDano) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cdDano}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_FAMILIA') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long cdDano) {
		invContDanoRepository.delete(cdDano);
	}

    @PutMapping("/{cdDano}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
	public ResponseEntity<InvContDano> atualizar(@PathVariable Long cdDano, @Valid @RequestBody InvContDano invContDano){
	   	InvContDano invContDanoSalva = invContDanoService.atualizar(cdDano, invContDano);
	    return ResponseEntity.ok(invContDanoSalva);
	}
	
}
