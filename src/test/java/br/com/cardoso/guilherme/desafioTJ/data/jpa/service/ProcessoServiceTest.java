package br.com.cardoso.guilherme.desafioTJ.data.jpa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Sets;

import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Comarca;
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

	@Captor
	private ArgumentCaptor<Processo> captor;
	
	private Long numeroUnico;
	private Processo processoExistente;
	private Processo processoRetornado;

	private Long comarcaVaraUnicaId;
	private Comarca comarcaVaraUnica;
	private Vara varaUnicaComTodasCompetencias;
	
	
	private Long comarcaComDuasVarasId;
	private Comarca comarcaComDuasVaras;
	private Vara primeiraVara;
	private Vara segundaVara;
	
	
	private Long comarcaComDuasVarasCompetenciasDistintasId;
	private Comarca comarcaComDuasVarasCompetenciasDistintas;
	private Vara primeiraVaraCompetenciaDistintas;
	private Vara segundaVaraCompetenciaDistintas;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


	private Set<Competencia> classeProcessualEscolhidaParaCadastro;

	private Processo processoSalvoEmComarcaDeVaraUnica;


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
		dadaUmaComarcaComVaraUnica();
		aoCriarNovoProcessoNaComarcaComVaraUnica();
		deveTerSalvadoProcessoComSucesso();
		eVaraDoProcessoSendoAVaraUnicaDaComarca();
		eComAClasseProcessualEscolhida();
	}
	
	@Test
	public void deveLancarProcessoEmVaraQueHouverMenosProcessos() {
		dadaUmaComarcaComDuasVarasQueAtendamTodasCompetencias();
		aoCriarNovoProcessoNaComarcaComDuasVaras();
		deveTerSalvadoProcessoComSucesso();
		eVaraDoProcessoSendoAVaraQueTemMenosProcessos();
		eComAClasseProcessualEscolhida();
	}
	
	@Test
	public void deveLancarExceptionSeNaoHouverVaraQueAtenda() {
		dadaUmaComarcaComDuasVarasQueAtendamCompetenciasDistintas();
		deveSerLancadaExceptionComMensagemDeErro();
		aoCriarNovoProcessoNaComarcaComDuasVarasCompetenciasDistintas();
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
		assertSame(processoExistente, processoRetornado);
	}
	
	
	private void dadaUmaComarcaComVaraUnica() {
		varaUnicaComTodasCompetencias = new Vara("Vara Com Todas Competencias", Sets.newHashSet(Competencia.values()));
		comarcaVaraUnica = new Comarca("Comarca Uma vara Com Todas Competencias ", Sets.newHashSet(varaUnicaComTodasCompetencias));
		comarcaVaraUnicaId = 1l;
		
		doReturn(comarcaVaraUnica).when(comarcaRepository).findById(comarcaVaraUnicaId);
	}
	
	private void aoCriarNovoProcessoNaComarcaComVaraUnica() {
		classeProcessualEscolhidaParaCadastro = Sets.newHashSet(Competencia.JUIZADO_ESPECIAL_CIVEL, Competencia.FEITOS_GERAIS_CIVEIS);
		processoService.criarProcesso(comarcaVaraUnicaId, classeProcessualEscolhidaParaCadastro);
	}
	
	private void deveTerSalvadoProcessoComSucesso() {
		verify(processoRepository).save(captor.capture());
	}
	
	private void eVaraDoProcessoSendoAVaraUnicaDaComarca() {
		processoSalvoEmComarcaDeVaraUnica = captor.getValue();
		assertSame(varaUnicaComTodasCompetencias, processoSalvoEmComarcaDeVaraUnica.getVara());
	}
	
	private void eComAClasseProcessualEscolhida() {
		assertEquals(classeProcessualEscolhidaParaCadastro, processoSalvoEmComarcaDeVaraUnica.getClasseProcessual());
	}

	private void dadaUmaComarcaComDuasVarasQueAtendamTodasCompetencias() {
		primeiraVara = new Vara("Primeira Todas Competencias", Sets.newHashSet(Competencia.values()));
		primeiraVara.addProcesso(new Processo(Sets.newHashSet(Competencia.DIRETORIA), primeiraVara));
		
		segundaVara = new Vara("Segunda Todas Competencias", Sets.newHashSet(Competencia.values()));
		
		comarcaComDuasVaras = new Comarca("Comarca Com duas Varas ", Sets.newHashSet(primeiraVara, segundaVara));
		comarcaComDuasVarasId = 2l;
		
		doReturn(comarcaComDuasVaras).when(comarcaRepository).findById(comarcaComDuasVarasId);
	}
	
	private void aoCriarNovoProcessoNaComarcaComDuasVaras() {
		classeProcessualEscolhidaParaCadastro = Sets.newHashSet(Competencia.JUIZADO_ESPECIAL_CIVEL, Competencia.FEITOS_GERAIS_CIVEIS);
		processoService.criarProcesso(comarcaComDuasVarasId, classeProcessualEscolhidaParaCadastro);
	}

	private void eVaraDoProcessoSendoAVaraQueTemMenosProcessos() {
		processoSalvoEmComarcaDeVaraUnica = captor.getValue();
		assertSame(segundaVara, processoSalvoEmComarcaDeVaraUnica.getVara());
	}
	
	private void dadaUmaComarcaComDuasVarasQueAtendamCompetenciasDistintas() {
		primeiraVaraCompetenciaDistintas = new Vara("Primeira Todas Competencias", Sets.newHashSet(Competencia.FEITOS_GERAIS_CIVEIS,
																								   Competencia.FAMILIA,
																								   Competencia.FAZENDA,
																								   Competencia.INFANCIA));
		
		segundaVaraCompetenciaDistintas = new Vara("Segunda Todas Competencias", Sets.newHashSet(Competencia.DIRETORIA,
																								 Competencia.FALENCIA,
																								 Competencia.JUIZADO_ESPECIAL_CRIME,
																								 Competencia.JUIZADO_ESPECIAL_CIVEL));
		
		comarcaComDuasVarasCompetenciasDistintas = new Comarca("Comarca Com duas Varas Competencias Distintas", Sets.newHashSet(primeiraVaraCompetenciaDistintas, segundaVaraCompetenciaDistintas));
		comarcaComDuasVarasCompetenciasDistintasId = 3l;
		
		doReturn(comarcaComDuasVarasCompetenciasDistintas).when(comarcaRepository).findById(comarcaComDuasVarasCompetenciasDistintasId);
	}
	
	private void aoCriarNovoProcessoNaComarcaComDuasVarasCompetenciasDistintas() {
		classeProcessualEscolhidaParaCadastro = Sets.newHashSet(Competencia.JUIZADO_ESPECIAL_CIVEL, Competencia.FEITOS_GERAIS_CIVEIS);
		processoService.criarProcesso(comarcaComDuasVarasCompetenciasDistintasId, classeProcessualEscolhidaParaCadastro);
	}
	
	private void deveSerLancadaExceptionComMensagemDeErro() {
	    thrown.expect(RuntimeException.class);
	    thrown.expectMessage("Não foi encontrada vara compatível com a classe processual para essa Comarca");
	}
}

