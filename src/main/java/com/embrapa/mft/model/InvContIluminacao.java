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
@Table(name = "d14_iluminacao") 
public class InvContIluminacao {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "d14_cdiluminacao")
	private Long cdIluminacao;
	
	@ManyToOne
	@JoinColumn(name = "d14_cdempresa", referencedColumnName = "d13_cdempresa")
	private CadEmpresa cdEmpresa;
	
	@Column(name = "d14_nmiluminacao")
	private String nmIluminacao;
	
	@Column(name = "d14_nmiluminacaoabreviatura")
	private String nmIluminacaoAbreviatura;

	public Long getCdIluminacao() {
		return cdIluminacao;
	}

	public void setCdIluminacao(Long cdIluminacao) {
		this.cdIluminacao = cdIluminacao;
	}

	public CadEmpresa getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(CadEmpresa cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public String getNmIluminacao() {
		return nmIluminacao;
	}

	public void setNmIluminacao(String nmIluminacao) {
		this.nmIluminacao = nmIluminacao;
	}

	public String getNmIluminacaoAbreviatura() {
		return nmIluminacaoAbreviatura;
	}

	public void setNmIluminacaoAbreviatura(String nmIluminacaoAbreviatura) {
		this.nmIluminacaoAbreviatura = nmIluminacaoAbreviatura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cdIluminacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvContIluminacao other = (InvContIluminacao) obj;
		return Objects.equals(cdIluminacao, other.cdIluminacao);
	}
	
	
}
