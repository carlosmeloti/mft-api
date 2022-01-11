package com.embrapa.mft.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "d19_classe_identificacao_fuste")
public class InvContClasseIdentificacaoFuste {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "d19_cdcif")
	private Long cdCif;
	
	@ManyToOne
	@JoinColumn(name = "d19_cdclassetamanho")
	private CadClasseDeTamanho cdClasseTamanho;
	
	@ManyToOne
	@JoinColumn(name = "d19_cdempresa")
	private CadEmpresa cdEmpresa;
	
	@Column(name = "d19_nmcif")
	private String nmCif;
	
	@Column(name = "d19_lgviva")
	private Boolean lgViva;
	
	@Column(name = "d19_lgdesaparecida")
	private Boolean lgDesaparecida;
	
	@Column(name = "d19_lgegressa")
	private Boolean lgEgressa;
	
	@Column(name = "d19_lgtemdescricaofuste")
	private Boolean lgTemDescricaoFuste;

	public Long getCdCif() {
		return cdCif;
	}

	public void setCdCif(Long cdCif) {
		this.cdCif = cdCif;
	}

	public CadClasseDeTamanho getCdClasseTamanho() {
		return cdClasseTamanho;
	}

	public void setCdClasseTamanho(CadClasseDeTamanho cdClasseTamanho) {
		this.cdClasseTamanho = cdClasseTamanho;
	}

	public CadEmpresa getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(CadEmpresa cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public String getNmCif() {
		return nmCif;
	}

	public void setNmCif(String nmCif) {
		this.nmCif = nmCif;
	}

	public Boolean isLgViva() {
		return lgViva;
	}

	public void setLgViva(Boolean lgViva) {
		this.lgViva = lgViva;
	}

	public Boolean isLgDesaparecida() {
		return lgDesaparecida;
	}

	public void setLgDesaparecida(Boolean lgDesaparecida) {
		this.lgDesaparecida = lgDesaparecida;
	}

	public Boolean isLgEgressa() {
		return lgEgressa;
	}

	public void setLgEgressa(Boolean lgEgressa) {
		this.lgEgressa = lgEgressa;
	}

	public Boolean isLgTemDescricaoFuste() {
		return lgTemDescricaoFuste;
	}

	public void setLgTemDescricaoFuste(Boolean lgTemDescricaoFuste) {
		this.lgTemDescricaoFuste = lgTemDescricaoFuste;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cdCif);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvContClasseIdentificacaoFuste other = (InvContClasseIdentificacaoFuste) obj;
		return cdCif == other.cdCif;
	}
	
	
	
	
	
	
	

}
