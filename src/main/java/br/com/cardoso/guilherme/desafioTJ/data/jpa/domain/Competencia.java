package br.com.cardoso.guilherme.desafioTJ.data.jpa.domain;

import java.util.Set;

import com.google.common.collect.Sets;

public enum Competencia {
	
	FEITOS_GERAIS_CIVEIS,
	FAMILIA,
	FAZENDA,
	INFANCIA,
	DIRETORIA,
	FALENCIA,
	JUIZADO_ESPECIAL_CIVEL,
	JUIZADO_ESPECIAL_CRIME;

	
	public static boolean contemTodasCompetenciasPossiveis(Set<Competencia> competencias) {
		return competencias.containsAll(Sets.newHashSet(values()));
	}
}
