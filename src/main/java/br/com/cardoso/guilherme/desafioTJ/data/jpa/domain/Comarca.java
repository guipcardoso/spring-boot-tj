package br.com.cardoso.guilherme.desafioTJ.data.jpa.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comarca implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String nome;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comarca")
	@JsonIgnore
	private Set<Vara> varas;

	protected Comarca() {
	}

	public Comarca(String nome, Set<Vara> varas) {
		this.nome = nome;
		this.varas = varas;
		Set<Competencia> competenciasDeTodasVaras = getCompetenciasDeTodasVaras();
		if (!Competencia.contemTodasCompetenciasPossiveis(competenciasDeTodasVaras)) {
			throw new IllegalArgumentException("Não é possível criar uma competência ");
		}
	}

	public Set<Competencia> getCompetenciasDeTodasVaras() {
		return this.varas.stream().flatMap(v->v.getCompetencias().stream()).collect(Collectors.toSet());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Set<Vara> getVaras() {
		return varas;
	}
	
}
