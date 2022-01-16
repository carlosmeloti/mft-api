package com.embrapa.mft.resource;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.embrapa.mft.event.RecursoCriadoEvent;
import com.embrapa.mft.model.CadEmpresa;
import com.embrapa.mft.model.ICClasseDeFloresta;
import com.embrapa.mft.model.InvContCIF;
import com.embrapa.mft.model.InvContCIFMedicoesAnt;
import com.embrapa.mft.repository.InvContCIFMedicoesAntRepository;
import com.embrapa.mft.repository.filter.InvContCIFMedicoesAntFilter;


@RestController
@RequestMapping("/cifanteriores") 
public class InvContCIFMedicoesAntResource {
	
	@Autowired
	private InvContCIFMedicoesAntRepository invContCIFMedicoesAntRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESPECIE') and #oauth2.hasScope('read')")
	public Page<InvContCIFMedicoesAnt> pesquisar(InvContCIFMedicoesAntFilter invContCIFMedicoesAntFilter,Pageable pageable){ 
    	CadEmpresa empresa = new CadEmpresa();
    	InvContCIF cif = new InvContCIF();
    	
    	empresa.setCdEmpresa(invContCIFMedicoesAntFilter.getCdEmpresa());
		cif.setCdCif(invContCIFMedicoesAntFilter.getCdCIFAtual());		
		return invContCIFMedicoesAntRepository.findByCdEmpresaAndCdCIFAtual(empresa, cif, pageable); 
    }
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESPECIE') and #oauth2.hasScope('write')")
	 public ResponseEntity<InvContCIFMedicoesAnt> criar(@RequestBody InvContCIFMedicoesAnt invContCIFMedicoesAnt, HttpServletResponse response){
		InvContCIFMedicoesAnt invContCIFMedicoesAntSalva = invContCIFMedicoesAntRepository.save(invContCIFMedicoesAnt);
		   publisher.publishEvent(new RecursoCriadoEvent(this, response, invContCIFMedicoesAntSalva.getCdCifAnteriorPK()));
		            return ResponseEntity.status(HttpStatus.CREATED).body(invContCIFMedicoesAntSalva);
	}
	
	@DeleteMapping("/{cdCifAnteriorPK}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_ESPECIE') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	 public void Remover(@PathVariable Long cdCifAnteriorPK) {
		invContCIFMedicoesAntRepository.delete(cdCifAnteriorPK);
	}

}
