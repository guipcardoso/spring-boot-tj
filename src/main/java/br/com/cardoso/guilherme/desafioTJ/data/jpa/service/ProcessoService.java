package br.com.cardoso.guilherme.desafioTJ.data.jpa.service;

import java.util.Collection;

import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Competencia;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Processo;

public interface ProcessoService {

	Processo obterProcesso(Long numeroUnico);
	
	Processo criarProcesso(Long comarcaId, Collection<Competencia> classeProcessual);

}
