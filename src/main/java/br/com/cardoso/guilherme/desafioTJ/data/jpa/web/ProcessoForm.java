package br.com.cardoso.guilherme.desafioTJ.data.jpa.web;

import java.io.Serializable;
import java.util.Collection;

import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Competencia;

public class ProcessoForm implements Serializable {

	private static final long serialVersionUID = 3393408927142001236L;

	private Long comarcaId;
	private Collection<Competencia> classeProcessual;
	
	public Long getComarcaId() {
		return comarcaId;
	}
	public void setComarcaId(Long comarcaId) {
		this.comarcaId = comarcaId;
	}
	
	public Collection<Competencia> getClasseProcessual() {
		return classeProcessual;
	}
	public void setClasseProcessual(Collection<Competencia> classeProcessual) {
		this.classeProcessual = classeProcessual;
	}
	
}
