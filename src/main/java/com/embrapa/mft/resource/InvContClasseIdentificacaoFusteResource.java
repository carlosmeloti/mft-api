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
import com.embrapa.mft.model.InvContClasseIdentificacaoFuste;
import com.embrapa.mft.repository.InvContClasseIdentificacaoFusteRepository;
import com.embrapa.mft.repository.filter.InvContClasseIdentificacaoFusteFilter;
import com.embrapa.mft.service.InvContClasseIdentificacaoFusteService;

@RestController
@RequestMapping("/cif")
public class InvContClasseIdentificacaoFusteResource {
	
	@Autowired
	private InvContClasseIdentificacaoFusteRepository  invContClasseIdentificacaoFusteRepository;
	
	@Autowired
	private InvContClasseIdentificacaoFusteService invContClasseIdentificacaoFusteService;
	
	@Autowired 
	private ApplicationEventPublisher eventPublisher;
	
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
	public Page<InvContClasseIdentificacaoFuste> pesquisar(InvContClasseIdentificacaoFusteFilter invContClasseIdentificacaoFusteFilter, Pageable pageable){
    	return invContClasseIdentificacaoFusteRepository.filtrar(invContClasseIdentificacaoFusteFilter, pageable);
    	

	}
    // Inserir_dados_na_tabela
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
   public ResponseEntity<InvContClasseIdentificacaoFuste> criar(@RequestBody InvContClasseIdentificacaoFuste invContClasseIdentificacaoFuste, HttpServletResponse response){
    	InvContClasseIdentificacaoFuste invContClasseIdentificacaoFusteSalva = invContClasseIdentificacaoFusteRepository.save(invContClasseIdentificacaoFuste);
    	 eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, invContClasseIdentificacaoFusteSalva.getCdCif()));
    	 
    	return ResponseEntity.status(HttpStatus.CREATED).body(invContClasseIdentificacaoFusteSalva);
    }
    
   // Buscar_culuna_especifica
    
    @GetMapping("/{cdFamilia}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_FAMILIA') and #oauth2.hasScope('read')")
    public ResponseEntity<InvContClasseIdentificacaoFuste> InvContClasseIdentificacaoFuste_Buscar_Pelo_Id(@PathVariable Long cdFamilia){
    	InvContClasseIdentificacaoFuste invContClasseIdentificacaoFuste= invContClasseIdentificacaoFusteRepository.findOne(cdFamilia);
    	  return invContClasseIdentificacaoFuste != null ? ResponseEntity.ok(invContClasseIdentificacaoFuste) : ResponseEntity.notFound().build();
    }
  //deleta_Id_especifica

    @DeleteMapping("/{cdfamilia}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_FAMILIA') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
     public void Remover(@PathVariable Long cdfamilia) {
    	invContClasseIdentificacaoFusteRepository.delete(cdfamilia);
    }

    @PutMapping("/{cdFamilia}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
     public ResponseEntity<InvContClasseIdentificacaoFuste> atualizar(@PathVariable Long cdFamilia, @Valid @RequestBody InvContClasseIdentificacaoFuste invContClasseIdentificacaoFuste){
    	 InvContClasseIdentificacaoFuste invContClasseIdentificacaoFusteSalva = invContClasseIdentificacaoFusteService.atualizar(cdFamilia, invContClasseIdentificacaoFuste);
    	    return ResponseEntity.ok(invContClasseIdentificacaoFusteSalva);
    }

}
