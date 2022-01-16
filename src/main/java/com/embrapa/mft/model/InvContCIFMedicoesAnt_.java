package com.embrapa.mft.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvContCIFMedicoesAnt.class)
public abstract class InvContCIFMedicoesAnt_ {
	
	
	public static volatile SingularAttribute<InvContCIFMedicoesAnt, Long> cdCifAnteriorPK;	
	public static volatile SingularAttribute<InvContCIFMedicoesAnt, CadEmpresa> cdEmpresa;
	public static volatile SingularAttribute<InvContCIFMedicoesAnt, CadClasseDeTamanho> cdClasseTamanho;
	public static volatile SingularAttribute<InvContCIFMedicoesAnt, InvContCIF> cdCIFAtual;
	public static volatile SingularAttribute<InvContCIFMedicoesAnt, InvContCIF> cdCIFAnterior;

		

}
