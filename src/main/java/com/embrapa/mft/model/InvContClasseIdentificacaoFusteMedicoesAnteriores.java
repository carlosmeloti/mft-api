package com.embrapa.mft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "r34_cif_atual_cif_anterior")
public class InvContClasseIdentificacaoFusteMedicoesAnteriores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r34_cdcifanteriorpk")
	private Long cdCifAnteriorPK;

}
