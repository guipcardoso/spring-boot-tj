package br.com.cardoso.guilherme.desafioTJ.data.jpa.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Sets;

import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Competencia;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Processo;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Vara;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.repository.ComarcaRepository;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.repository.ProcessoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProcessoServiceTest {
	
	private ProcessoService processoService;
	
	@Mock
	private ProcessoRepository processoRepository;
	
	@Mock
	private ComarcaRepository comarcaRepository;

	
	private Long numeroUnico;
	private Processo processoExistente;
	private Processo processoRetornado;


	@Before
	public void setup() {
		processoService = new ProcessoServiceImpl(processoRepository, comarcaRepository);
	}
	
	@Test
	public void deveBuscarProcessoDoRepositorio() {
		dadoUmProcessoExistente();
		aoBuscarPorSeuNumeroUnico();
		deveTerBuscadoNoRepositorioPeloNumeroUnico();
		eProcessoRetornadoDeveSerOMesmoRetornadoDoRepositorio();
	}
	
	@Test
	public void deveLancarProcessoEmComarcaComVaraUnica() {
		
	}
	
	
	private void dadoUmProcessoExistente() {
		numeroUnico = 26l;
		processoExistente = new Processo(Sets.newHashSet(Competencia.JUIZADO_ESPECIAL_CIVEL), new Vara("Vara Teste", Sets.newHashSet(Competencia.JUIZADO_ESPECIAL_CIVEL)));
		doReturn(processoExistente).when(processoRepository).findByNumeroUnico(numeroUnico);
	}
	
	private void aoBuscarPorSeuNumeroUnico() {
		processoRetornado = processoService.obterProcesso(numeroUnico);
	}
	
	private void deveTerBuscadoNoRepositorioPeloNumeroUnico() {
		verify(processoRepository).findByNumeroUnico(numeroUnico);
	}
	
	private void eProcessoRetornadoDeveSerOMesmoRetornadoDoRepositorio() {
		Assert.assertSame(processoExistente, processoRetornado);
	}
	
	
}
