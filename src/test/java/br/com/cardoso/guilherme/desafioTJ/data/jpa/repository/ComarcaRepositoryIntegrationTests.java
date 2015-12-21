package br.com.cardoso.guilherme.desafioTJ.data.jpa.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.cardoso.guilherme.desafioTJ.data.jpa.DistribuicaoProcessosTJApplication;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Comarca;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Competencia;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DistribuicaoProcessosTJApplication.class)
@Transactional(readOnly = true)
public class ComarcaRepositoryIntegrationTests {

	@Autowired
	ComarcaRepository repository;

	@Test
	public void comarcasDevemPossuirTodasCompetencias() {

		//given
		List<Comarca> comarcas = this.repository.findAll();
		
		for (Comarca comarca : comarcas) {
			//when
			Set<Competencia> competenciasDeTodasVaras = comarca.getCompetenciasDeTodasVaras();
			
			//then
			assertTrue(Competencia.contemTodasCompetenciasPossiveis(competenciasDeTodasVaras));
		}
	}
}
