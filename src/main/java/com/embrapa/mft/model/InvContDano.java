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
@Table(name = "d11_dano") 
public class InvContDano {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "d11_cddano")
	private Long cdDano;
	
	@ManyToOne
	@JoinColumn(name = "d11_cdempresa", referencedColumnName = "d13_cdempresa")
	private CadEmpresa cdEmpresa;
	
	@Column(name = "d11_nmdano")
	private String nmDano;
	
	@Column(name = "d11_nmdanoabreviatura")
	private String nmDanoAbreviatura;

	public Long getCdDano() {
		return cdDano;
	}

	public void setCdDano(Long cdDano) {
		this.cdDano = cdDano;
	}

	public CadEmpresa getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(CadEmpresa cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public String getNmDano() {
		return nmDano;
	}

	public void setNmDano(String nmDano) {
		this.nmDano = nmDano;
	}

	public String getNmDanoAbreviatura() {
		return nmDanoAbreviatura;
	}

	public void setNmDanoAbreviatura(String nmDanoAbreviatura) {
		this.nmDanoAbreviatura = nmDanoAbreviatura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cdDano);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvContDano other = (InvContDano) obj;
		return Objects.equals(cdDano, other.cdDano);
	}
	
	
	
	
	
}
