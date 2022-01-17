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
@Table(name = "d12_podridao") 
public class InvContPodridao {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "d12_cdpodridao")
	private Long cdPodridao;
	
	@ManyToOne
	@JoinColumn(name = "d12_cdempresa", referencedColumnName = "d13_cdempresa")
	private CadEmpresa cdEmpresa;
	
	@Column(name = "d12_nmpodridao")
	private String nmPodridao;
	
	@Column(name = "d12_nmpodridaoabreviatura")
	private String nmPodridaoAbreviatura;

	public Long getCdPodridao() {
		return cdPodridao;
	}

	public void setCdPodridao(Long cdPodridao) {
		this.cdPodridao = cdPodridao;
	}

	public CadEmpresa getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(CadEmpresa cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public String getNmPodridao() {
		return nmPodridao;
	}

	public void setNmPodridao(String nmPodridao) {
		this.nmPodridao = nmPodridao;
	}

	public String getNmPodridaoAbreviatura() {
		return nmPodridaoAbreviatura;
	}

	public void setNmPodridaoAbreviatura(String nmPodridaoAbreviatura) {
		this.nmPodridaoAbreviatura = nmPodridaoAbreviatura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cdPodridao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvContPodridao other = (InvContPodridao) obj;
		return Objects.equals(cdPodridao, other.cdPodridao);
	}

		
}
