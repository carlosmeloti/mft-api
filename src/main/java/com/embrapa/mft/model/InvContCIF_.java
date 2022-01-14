package com.embrapa.mft.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvContCIF.class)
public abstract class InvContCIF_ {
	
	public static volatile SingularAttribute<InvContCIF, Long> cdCif;	
	public static volatile SingularAttribute<InvContCIF, CadClasseDeTamanho> cdClasseTamanho;
	public static volatile SingularAttribute<InvContCIF, CadEmpresa> cdEmpresa;
	public static volatile SingularAttribute<InvContCIF, String> nmCif;
	public static volatile SingularAttribute<InvContCIF, Boolean> lgViva;
	public static volatile SingularAttribute<InvContCIF, Boolean> lgDesaparecida;
	public static volatile SingularAttribute<InvContCIF, Boolean> lgEgressa;
	public static volatile SingularAttribute<InvContCIF, Boolean> lgTemDescricaoFuste;

}
