package br.com.cardoso.guilherme.desafioTJ.data.jpa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DistribuicaoProcessosTJApplication.class)
@WebAppConfiguration
@ActiveProfiles("scratch")
public class SampleDataJpaApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testHome() throws Exception {

		this.mvc.perform(get("/processo/1"))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"numeroUnico\":1,\"classeProcessual\":[\"FAMILIA\",\"FEITOS_GERAIS_CIVEIS\"],\"vara\":{\"id\":1,\"nome\":\"Vara 1 de Cuiabá\",\"competencias\":[\"INFANCIA\",\"FAMILIA\",\"FEITOS_GERAIS_CIVEIS\",\"FAZENDA\"],\"comarca\":{\"id\":1,\"nome\":\"Cuiabá\",\"competenciasDeTodasVaras\":[\"JUIZADO_ESPECIAL_CRIME\",\"INFANCIA\",\"FAMILIA\",\"FALENCIA\",\"JUIZADO_ESPECIAL_CIVEL\",\"FEITOS_GERAIS_CIVEIS\",\"FAZENDA\",\"DIRETORIA\"]},\"quantidadeDeProcessos\":3}}"));
	}
}
