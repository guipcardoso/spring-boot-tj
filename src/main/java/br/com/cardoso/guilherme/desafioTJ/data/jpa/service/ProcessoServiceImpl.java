package br.com.cardoso.guilherme.desafioTJ.data.jpa.service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;

import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Comarca;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Competencia;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Processo;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Vara;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.repository.ComarcaRepository;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.repository.ProcessoRepository;

@Component("processoService")
@Transactional
class ProcessoServiceImpl implements ProcessoService {

	private final ProcessoRepository processoRepository;
	private final ComarcaRepository comarcaRepository;


	@Autowired
	public ProcessoServiceImpl(ProcessoRepository processoRepository, ComarcaRepository comarcaRepository) {
		this.processoRepository = processoRepository;
		this.comarcaRepository = comarcaRepository;
	}

	@Override
	public Processo obterProcesso(Long numeroUnico) {
		return processoRepository.findByNumeroUnico(numeroUnico);
	}

	@Override
	public Processo criarProcesso(Long comarcaId, Collection<Competencia> classseProcessual) {
		Comarca comarca = comarcaRepository.findById(comarcaId);
		Set<Competencia> classeProcessual = Sets.newHashSet(classseProcessual);
		
		
		Optional<Vara> vara = comarca.getVaras()
						   .stream()
						   .filter(v->v.atendeClasseProcessual(classeProcessual))
						   .min((v1, v2) -> Integer.compare(v1.getQuantidadeDeProcessos(), v2.getQuantidadeDeProcessos()));
		
		if (!vara.isPresent()) throw new RuntimeException("Não foi encontrada vara compatível com a classe processual para essa Comarca");
		
		Processo processo = new Processo(classeProcessual, vara.get());
		return processoRepository.save(processo);
	}

}
