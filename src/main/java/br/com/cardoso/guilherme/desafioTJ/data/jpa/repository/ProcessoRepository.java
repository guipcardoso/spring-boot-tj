package br.com.cardoso.guilherme.desafioTJ.data.jpa.repository;

import org.springframework.data.repository.Repository;

import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Processo;

public interface ProcessoRepository extends Repository<Processo, Long> {

	Processo findByNumeroUnico(Long numeroUnico);

	Processo save(Processo processo);

}
