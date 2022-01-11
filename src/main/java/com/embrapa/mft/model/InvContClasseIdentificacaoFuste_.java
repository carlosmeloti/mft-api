package com.embrapa.mft.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvContClasseIdentificacaoFuste.class)
public abstract class InvContClasseIdentificacaoFuste_ {
	
	public static volatile SingularAttribute<InvContClasseIdentificacaoFuste, Long> cdCif;	
	public static volatile SingularAttribute<InvContClasseIdentificacaoFuste, CadClasseDeTamanho> cdClasseTamanho;
	public static volatile SingularAttribute<InvContClasseIdentificacaoFuste, CadEmpresa> cdEmpresa;
	public static volatile SingularAttribute<InvContClasseIdentificacaoFuste, String> nmCif;
	public static volatile SingularAttribute<InvContClasseIdentificacaoFuste, Boolean> lgViva;
	public static volatile SingularAttribute<InvContClasseIdentificacaoFuste, Boolean> lgDesaparecida;
	public static volatile SingularAttribute<InvContClasseIdentificacaoFuste, Boolean> lgEgressa;
	public static volatile SingularAttribute<InvContClasseIdentificacaoFuste, Boolean> lgTemDescricaoFuste;

}
