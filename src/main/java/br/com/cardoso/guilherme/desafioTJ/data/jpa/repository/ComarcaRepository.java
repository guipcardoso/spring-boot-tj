package br.com.cardoso.guilherme.desafioTJ.data.jpa.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Comarca;

public interface ComarcaRepository extends Repository<Comarca, Long> {

	Comarca findById(Long id);
	
	List<Comarca> findAll();


}
