/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
