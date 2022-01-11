package com.embrapa.mft.repository.consultas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.embrapa.mft.model.CadEmpresa;
import com.embrapa.mft.model.CadEmpresa_;
import com.embrapa.mft.model.InvContClasseIdentificacaoFuste;
import com.embrapa.mft.model.InvContClasseIdentificacaoFuste_;
import com.embrapa.mft.repository.filter.CadEmpresaFilter;
import com.embrapa.mft.repository.filter.InvContClasseIdentificacaoFusteFilter;

public class InvContClasseIdentificacaoFusteImpl implements InvContClasseIdentificacaoFusteQuery{

	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public Page<InvContClasseIdentificacaoFuste> filtrar(
			InvContClasseIdentificacaoFusteFilter invContClasseIdentificacaoFusteFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<InvContClasseIdentificacaoFuste> criteria = builder .createQuery(InvContClasseIdentificacaoFuste.class);
		Root<InvContClasseIdentificacaoFuste> root = criteria.from(InvContClasseIdentificacaoFuste.class);
		
		Predicate[] predicates = criarRestricoes(invContClasseIdentificacaoFusteFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<InvContClasseIdentificacaoFuste> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(invContClasseIdentificacaoFusteFilter));
	}
	
	private Long total(InvContClasseIdentificacaoFusteFilter invContClasseIdentificacaoFusteFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<InvContClasseIdentificacaoFuste> root = criteria.from(InvContClasseIdentificacaoFuste.class);
		
		Predicate[] predicates = criarRestricoes(invContClasseIdentificacaoFusteFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}


	private void adiconarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalDeRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDeRegistrosPorPagina);
		
	}


	private Predicate[] criarRestricoes(InvContClasseIdentificacaoFusteFilter invContClasseIdentificacaoFusteFilter, CriteriaBuilder builder,
			Root<InvContClasseIdentificacaoFuste> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(invContClasseIdentificacaoFusteFilter.getNmCif())) {
			predicates.add(builder.like(
					builder.lower(root.get(InvContClasseIdentificacaoFuste_.nmCif)), "%" + invContClasseIdentificacaoFusteFilter.getNmCif().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
