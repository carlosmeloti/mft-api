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
@Table(name = "d15_forma_copa") 
public class InvContFormaCopa {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "d15_cdforma")
	private Long cdFormaCopa;
	
	@ManyToOne
	@JoinColumn(name = "d15_cdempresa", referencedColumnName = "d13_cdempresa")
	private CadEmpresa cdEmpresa;
	
	@Column(name = "d15_nmforma")
	private String nmFormaCopa;
	
	@Column(name = "d15_nmformaabreviatura")
	private String nmFormaCopaAbreviatura;

	public Long getCdFormaCopa() {
		return cdFormaCopa;
	}

	public void setCdFormaCopa(Long cdFormaCopa) {
		this.cdFormaCopa = cdFormaCopa;
	}

	public CadEmpresa getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(CadEmpresa cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public String getNmFormaCopa() {
		return nmFormaCopa;
	}

	public void setNmFormaCopa(String nmFormaCopa) {
		this.nmFormaCopa = nmFormaCopa;
	}

	public String getNmFormaCopaAbreviatura() {
		return nmFormaCopaAbreviatura;
	}

	public void setNmFormaCopaAbreviatura(String nmFormaCopaAbreviatura) {
		this.nmFormaCopaAbreviatura = nmFormaCopaAbreviatura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cdFormaCopa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvContFormaCopa other = (InvContFormaCopa) obj;
		return Objects.equals(cdFormaCopa, other.cdFormaCopa);
	}

		
}
