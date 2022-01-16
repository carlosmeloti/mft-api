package com.embrapa.mft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "r34_cif_atual_cif_anterior")
public class InvContCIFMedicoesAnt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r34_cdcifanteriorpk")
	private Long cdCifAnteriorPK;
	
	@ManyToOne
	@JoinColumn(name = "r34_cdempresa", referencedColumnName = "d13_cdempresa")
	private CadEmpresa cdEmpresa;
	

	@ManyToOne
	@JoinColumn(name = "r34_cdclassetamanho", referencedColumnName = "d10_cdclassetamanho")
	private CadClassTamanhoIndividuo cdClasseTamanho;
	
	
	@ManyToOne
	@JoinColumn(name = "r34_cdcifatual", referencedColumnName = "d19_cdcif")
	private InvContCIF cdCIFAtual;
	
	@ManyToOne
	@JoinColumn(name = "r34_cdcifanterior", referencedColumnName = "d19_cdcif")
	private InvContCIF cdCIFAnterior;

	public Long getCdCifAnteriorPK() {
		return cdCifAnteriorPK;
	}

	public void setCdCifAnteriorPK(Long cdCifAnteriorPK) {
		this.cdCifAnteriorPK = cdCifAnteriorPK;
	}

	public CadEmpresa getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(CadEmpresa cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public CadClassTamanhoIndividuo getCdClasseTamanho() {
		return cdClasseTamanho;
	}

	public void setCdClasseTamanho(CadClassTamanhoIndividuo cdClasseTamanho) {
		this.cdClasseTamanho = cdClasseTamanho;
	}

	public InvContCIF getCdCIFAtual() {
		return cdCIFAtual;
	}

	public void setCdCIFAtual(InvContCIF cdCIFAtual) {
		this.cdCIFAtual = cdCIFAtual;
	}

	public InvContCIF getCdCIFAnterior() {
		return cdCIFAnterior;
	}

	public void setCdCIFAnterior(InvContCIF cdCIFAnterior) {
		this.cdCIFAnterior = cdCIFAnterior;
	}
	
	
	
	
	
	

}
