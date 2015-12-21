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
