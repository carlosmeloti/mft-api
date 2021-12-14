package com.embrapa.mft.resource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.embrapa.mft.event.RecursoCriadoEvent;
import com.embrapa.mft.model.CadAmf;
import com.embrapa.mft.model.CadEmpresa;
import com.embrapa.mft.model.CadParcela;
import com.embrapa.mft.model.CadSubParcela;
import com.embrapa.mft.model.CadTipoParcela;
import com.embrapa.mft.model.fnc.GeraParcelESubParcela;
import com.embrapa.mft.repository.CadParcelaRepository;
import com.embrapa.mft.repository.CadSubParcelaRepository;
import com.embrapa.mft.repository.procedimentos.GeraParcelESubParcelaRepository;

@RestController
@RequestMapping("/geraparcelaesubparcelas")
public class GeraParcelESubParcelaResource {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private GeraParcelESubParcelaRepository geraParcelESubParcelaRepository;
	
	@Autowired
	private CadParcelaRepository cadParcelaRepository;
	
	@Autowired
	private CadSubParcelaRepository cadSubParcelaRepository;
	
		
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EMPRESA') and #oauth2.hasScope('write')")
	public ResponseEntity<GeraParcelESubParcela> criar(@RequestBody GeraParcelESubParcela geraParcelESubParcela, HttpServletResponse response) {
		System.out.println("teste");
		GeraParcelESubParcela geraParcelESubParcelaSalva = geraParcelESubParcelaRepository.save(geraParcelESubParcela); 
		publisher.publishEvent(new RecursoCriadoEvent(this, response, geraParcelESubParcelaSalva.getId()));
		
		int contapar;
		int contasubpar;
		int qtdexiste;
		int qtdexisteSub;
		
		contapar = geraParcelESubParcelaSalva.getCdParcelaInicio();
		
				
		while(contapar < geraParcelESubParcelaSalva.getCdParcelaInicio() + geraParcelESubParcelaSalva.getNrParcelas()) {
			
			qtdexiste = geraParcelESubParcelaRepository.qtdexiste(geraParcelESubParcelaSalva.getCdEmpresa(), 
					geraParcelESubParcelaSalva.getCdArea(), contapar);	
			
			
			if(qtdexiste == 0) {
				CadParcela parcela = new CadParcela();
				CadAmf amf = new CadAmf();
				CadEmpresa empresa = new CadEmpresa();
				CadTipoParcela tipoParcela = new CadTipoParcela();
				
				amf.setCdarea(geraParcelESubParcelaSalva.getCdArea());
				empresa.setCdEmpresa(geraParcelESubParcelaSalva.getCdEmpresa());
				tipoParcela.setCdTipoParcela(geraParcelESubParcelaSalva.getCdTipoParcela());
				
				parcela.setCdArea(amf);
				parcela.setCdEmpresa(empresa);
				parcela.setCdParcela(new Long(contapar));
				parcela.setCdTipoParcela(tipoParcela);
				parcela.setLgTestemunha(false);
				
				cadParcelaRepository.save(parcela);
			}
			
			contasubpar = 1;
			while(contasubpar <= geraParcelESubParcelaSalva.getNrSubParcelasPorParcelas()) {
				
				qtdexisteSub = geraParcelESubParcelaRepository.qtdexisteSub(geraParcelESubParcelaSalva.getCdEmpresa(),geraParcelESubParcela.getCdArea(), contapar,contasubpar);
				
				if(qtdexisteSub == 0) {
					
					CadAmf amf = new CadAmf();
					CadEmpresa empresa = new CadEmpresa();
					CadParcela parcela = new CadParcela();
					
					amf.setCdarea(geraParcelESubParcelaSalva.getCdArea());
					empresa.setCdEmpresa(geraParcelESubParcelaSalva.getCdEmpresa());
					parcela.setCdParcela(new Long(contapar));
					
					CadSubParcela subParcela = new CadSubParcela();
					
					subParcela.setCdSubParcela(new Long(contasubpar));
					subParcela.setCdEmpresa(empresa);
					subParcela.setCdParcela(new Long(contapar));
					subParcela.setCdArea(amf);
					
					
					cadSubParcelaRepository.save(subParcela); 
					
				}
				
				contasubpar++;
			}
			contapar++;
		}
		
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(geraParcelESubParcelaSalva);
	}
	
	

}
