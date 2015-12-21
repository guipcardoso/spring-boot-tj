package br.com.cardoso.guilherme.desafioTJ.data.jpa.domain;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class ComarcaTest {

	@Test
	public void deveInstaciarComarcaCorretamenteAoPossuirUmaVaraComTodasCompetencias() {
		Set<Competencia> competencias = Sets.newHashSet(Competencia.values());
		Set<Vara> varas = Sets.newHashSet(new Vara("Vara Com Todas Competencias", competencias));
		new Comarca("Comarca Uma vara Com Todas Competencias ", varas);
	}

	@Test
	public void deveInstaciarComarcaCorretamenteAoPossuirMaisDeUmaVaraComTodasCompetenciasDistribuidas() {
		Vara vara1 = new Vara("Vara 1 Metade Completa", Sets.newHashSet(Competencia.FEITOS_GERAIS_CIVEIS,
																	    Competencia.FAMILIA,
																	    Competencia.FAZENDA,
																	    Competencia.INFANCIA));

		Vara vara2 = new Vara("Vara 2 Metade Completa", Sets.newHashSet(Competencia.DIRETORIA,
																	    Competencia.FALENCIA,
																	    Competencia.JUIZADO_ESPECIAL_CIVEL,
																	    Competencia.JUIZADO_ESPECIAL_CRIME));

		Set<Vara> varas = Sets.newHashSet(vara1, vara2);
		new Comarca("Comarca Duas varas Competencias Completas", varas);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveJogarIllegalArgumentExceptionAoTentarInstanciarComarcaComUmaVaraSemTodasCompetencias() {
		Set<Competencia> competencias = Sets.newHashSet(Competencia.FEITOS_GERAIS_CIVEIS,
													    Competencia.FAMILIA,
													    Competencia.FAZENDA,
													    Competencia.INFANCIA,
													    Competencia.FALENCIA,
													    Competencia.JUIZADO_ESPECIAL_CIVEL,
														Competencia.JUIZADO_ESPECIAL_CRIME);

		Set<Vara> varas = Sets.newHashSet(new Vara("Vara Com Uma Competencia a Menos", competencias));
		new Comarca("Comarca Uma Vara Competencias Incompletas", varas);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveJogarIllegalArgumentExceptionAoTentarInstanciarComarcaComMaisDeUmaVaraSemTodasCompetenciasDistribuidas() {
		Vara vara1 = new Vara("Vara 1 Metade Completa", Sets.newHashSet(Competencia.FEITOS_GERAIS_CIVEIS,
																	    Competencia.FAMILIA,
																	    Competencia.FAZENDA,
																	    Competencia.INFANCIA));

		Vara vara2 = new Vara("Vara 2 Metade Incompleta", Sets.newHashSet(Competencia.FALENCIA,
								 										  Competencia.JUIZADO_ESPECIAL_CIVEL,
								 										  Competencia.JUIZADO_ESPECIAL_CRIME));

		Set<Vara> varas = Sets.newHashSet(vara1, vara2);
		new Comarca("Comarca Duas Varas Competencias Incompletas", varas);
	}

}
