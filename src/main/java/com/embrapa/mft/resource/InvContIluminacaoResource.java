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
import com.embrapa.mft.model.InvContIluminacao;
import com.embrapa.mft.repository.InvContIluminacaoRepository;
import com.embrapa.mft.repository.filter.InvContIluminacaoFilter;
import com.embrapa.mft.service.InvContIluminacaoService;

@RestController
@RequestMapping("/iluminacao")
public class InvContIluminacaoResource {

	@Autowired
	private InvContIluminacaoRepository invContIluminacaoRepository; 
	
	@Autowired
	private InvContIluminacaoService invContIluminacaoService;
	
	@Autowired 
	private ApplicationEventPublisher eventPublisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESPECIE') and #oauth2.hasScope('read')")
	public Page<InvContIluminacao> pesquisar(InvContIluminacaoFilter invContIluminacaoFilterFilter, Pageable pageable){ 
    	CadEmpresa empresa = new CadEmpresa();    	
    	empresa.setCdEmpresa(invContIluminacaoFilterFilter.getCdEmpresa());	
		return invContIluminacaoRepository.findByCdEmpresa(empresa, pageable); 
    }
	
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
    public ResponseEntity<InvContIluminacao> criar(@RequestBody InvContIluminacao invContIluminacao, HttpServletResponse response){
    	InvContIluminacao invContIluminacaoSalva = invContIluminacaoRepository.save(invContIluminacao);
    	eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, invContIluminacaoSalva.getCdIluminacao()));
	    	 
	    return ResponseEntity.status(HttpStatus.CREATED).body(invContIluminacaoSalva);
	}
	    
	    
	@GetMapping("/{cdIluminacao}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FAMILIA') and #oauth2.hasScope('read')")
	public ResponseEntity<InvContIluminacao> buscarPorCodigo(@PathVariable Long cdIluminacao){
		InvContIluminacao invContIluminacao= invContIluminacaoRepository.findOne(cdIluminacao);
	    return invContIluminacao != null ? ResponseEntity.ok(invContIluminacao) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cdIluminacao}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_FAMILIA') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long cdIluminacao) {
		invContIluminacaoRepository.delete(cdIluminacao);
	}

    @PutMapping("/{cdIluminacao}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
	public ResponseEntity<InvContIluminacao> atualizar(@PathVariable Long cdIluminacao, @Valid @RequestBody InvContIluminacao invContIluminacao){
	   	InvContIluminacao invContIluminacaoSalva = invContIluminacaoService.atualizar(cdIluminacao, invContIluminacao);
	    return ResponseEntity.ok(invContIluminacaoSalva);
	}
	
}
