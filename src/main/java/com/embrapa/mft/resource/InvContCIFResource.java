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
import com.embrapa.mft.model.InvContCIF;
import com.embrapa.mft.repository.InvContCIFRepository;
import com.embrapa.mft.repository.filter.InvContCIFFilter;
import com.embrapa.mft.service.InvContCIFService;

@RestController
@RequestMapping("/cif")
public class InvContCIFResource {
	
	@Autowired
	private InvContCIFRepository invContCIFRepository;
	
	@Autowired
	private InvContCIFService invContCifService;
	
	@Autowired 
	private ApplicationEventPublisher eventPublisher;
	
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
	public Page<InvContCIF> pesquisar(InvContCIFFilter invContClasseIdentificacaoFusteFilter, Pageable pageable){
    	return invContCIFRepository.filtrar(invContClasseIdentificacaoFusteFilter, pageable);
    	

	}
    // Inserir_dados_na_tabela
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
   public ResponseEntity<InvContCIF> criar(@RequestBody InvContCIF invContClasseIdentificacaoFuste, HttpServletResponse response){
    	InvContCIF invContClasseIdentificacaoFusteSalva = invContCIFRepository.save(invContClasseIdentificacaoFuste);
    	 eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, invContClasseIdentificacaoFusteSalva.getCdCif()));
    	 
    	return ResponseEntity.status(HttpStatus.CREATED).body(invContClasseIdentificacaoFusteSalva);
    }
    
   // Buscar_culuna_especifica
    
    @GetMapping("/{cdFamilia}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_FAMILIA') and #oauth2.hasScope('read')")
    public ResponseEntity<InvContCIF> InvContClasseIdentificacaoFuste_Buscar_Pelo_Id(@PathVariable Long cdFamilia){
    	InvContCIF invContClasseIdentificacaoFuste= invContCIFRepository.findOne(cdFamilia);
    	  return invContClasseIdentificacaoFuste != null ? ResponseEntity.ok(invContClasseIdentificacaoFuste) : ResponseEntity.notFound().build();
    }
  //deleta_Id_especifica

    @DeleteMapping("/{cdfamilia}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_FAMILIA') and #oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
     public void Remover(@PathVariable Long cdfamilia) {
    	invContCIFRepository.delete(cdfamilia);
    }

    @PutMapping("/{cdFamilia}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_FAMILIA') and #oauth2.hasScope('write')")
     public ResponseEntity<InvContCIF> atualizar(@PathVariable Long cdFamilia, @Valid @RequestBody InvContCIF invContClasseIdentificacaoFuste){
    	 InvContCIF invContClasseIdentificacaoFusteSalva = invContCifService.atualizar(cdFamilia, invContClasseIdentificacaoFuste);
    	    return ResponseEntity.ok(invContClasseIdentificacaoFusteSalva);
    }


}
