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
