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

import com.embrapa.mft.model.InvContCIF;
import com.embrapa.mft.model.InvContCIF_;
import com.embrapa.mft.repository.filter.InvContCIFFilter;

public class InvContCIFRepositoryImpl implements InvContCIFRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<InvContCIF> filtrar(InvContCIFFilter invContCIFFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<InvContCIF> criteria = builder .createQuery(InvContCIF.class);
		Root<InvContCIF> root = criteria.from(InvContCIF.class);
		
		Predicate[] predicates = criarRestricoes(invContCIFFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<InvContCIF> query = manager.createQuery(criteria);
		adiconarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(invContCIFFilter));
	}
	
	private Long total(InvContCIFFilter invContCIFFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<InvContCIF> root = criteria.from(InvContCIF.class);
		
		Predicate[] predicates = criarRestricoes(invContCIFFilter, builder, root);
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


	private Predicate[] criarRestricoes(InvContCIFFilter invContCIFFilter, CriteriaBuilder builder,
			Root<InvContCIF> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(invContCIFFilter.getNmCif())) {
			predicates.add(builder.like(
					builder.lower(root.get(InvContCIF_.nmCif)), "%" + invContCIFFilter.getNmCif().toLowerCase() + "%"));
		}if (invContCIFFilter.getCdEmpresa() != null) {
			predicates.add(
					builder.equal(root.get(InvContCIF_.cdEmpresa), invContCIFFilter.getCdEmpresa()));
        }
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
