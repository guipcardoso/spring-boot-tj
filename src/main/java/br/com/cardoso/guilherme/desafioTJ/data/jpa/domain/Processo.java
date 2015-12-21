package br.com.cardoso.guilherme.desafioTJ.data.jpa.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Processo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long numeroUnico;

	@ElementCollection(targetClass= Competencia.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="processo_competencia")
    @Column(name="competencia")
	private Set<Competencia> classeProcessual;
	
	@ManyToOne
	private Vara vara;

	protected Processo() {
	}

	public Processo(Set<Competencia> classeProcessual, Vara vara) {
		this.classeProcessual = classeProcessual;
		this.vara = vara;
	}

	public Long getNumeroUnico() {
		return numeroUnico;
	}

	public Set<Competencia> getClasseProcessual() {
		return classeProcessual;
	}

	public Vara getVara() {
		return vara;
	}

}
