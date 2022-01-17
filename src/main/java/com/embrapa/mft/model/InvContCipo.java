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
@Table(name = "d16_cipo") 
public class InvContCipo {  
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "d16_cdcipo")
	private Long cdCipo;
	
	@ManyToOne
	@JoinColumn(name = "d16_cdempresa", referencedColumnName = "d13_cdempresa")
	private CadEmpresa cdEmpresa;
	
	@Column(name = "d16_nmcipo")
	private String nmCipo;
	
	@Column(name = "d16_nmcipoabreviatura")
	private String nmCipoAbreviatura;

	public Long getCdCipo() {
		return cdCipo;
	}

	public void setCdCipo(Long cdCipo) {
		this.cdCipo = cdCipo;
	}

	public CadEmpresa getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(CadEmpresa cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public String getNmCipo() {
		return nmCipo;
	}

	public void setNmCipo(String nmCipo) {
		this.nmCipo = nmCipo;
	}

	public String getNmCipoAbreviatura() {
		return nmCipoAbreviatura;
	}

	public void setNmCipoAbreviatura(String nmCipoAbreviatura) {
		this.nmCipoAbreviatura = nmCipoAbreviatura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cdCipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvContCipo other = (InvContCipo) obj;
		return Objects.equals(cdCipo, other.cdCipo);
	}

		
}
