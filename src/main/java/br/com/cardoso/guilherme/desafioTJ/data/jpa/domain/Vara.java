package br.com.cardoso.guilherme.desafioTJ.data.jpa.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

@Entity
public class Vara implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String nome;

	@ElementCollection(targetClass= Competencia.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="vara_competencia")
    @Column(name="competencia")
	private Set<Competencia> competencias;

	@ManyToOne
	private Comarca comarca;

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "vara")
	@JsonIgnore
	private Set<Processo> processos = Sets.newHashSet();
	
	protected Vara() {
	}

	public Vara(String nome, Set<Competencia> competencias) {
		this.nome = nome;
		this.competencias = competencias;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Set<Competencia> getCompetencias() {
		return competencias;
	}
	
	public Comarca getComarca() {
		return comarca;
	}

	public void addProcesso(Processo processo) {
		processos.add(processo);
	}
	
	public Set<Processo> getProcessos() {
		return processos;
	}

	public Integer getQuantidadeDeProcessos() {
		return processos == null ? 0 : processos.size();
	}

	public boolean atendeClasseProcessual(Set<Competencia> classeProcessual) {
		return competencias.containsAll(classeProcessual);
	}
}
