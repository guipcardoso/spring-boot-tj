package br.com.cardoso.guilherme.desafioTJ.data.jpa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
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
	public void testUrlGetProcesso() throws Exception {
		this.mvc.perform(get("/processos/1"))
				.andExpect(status().isOk());
	}

	@Test
	public void testUrlPostProcessoVaraComCompetenciasExistentes() throws Exception {
		this.mvc.perform(post("/processos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"comarcaId\": 1, \"classeProcessual\": [\"FAMILIA\", \"FEITOS_GERAIS_CIVEIS\"]}"))
				.andExpect(status().isOk());
	}
	

	@Test
	public void testUrlPostProcessoVaraInexistente() throws Exception {
		this.mvc.perform(post("/processos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"comarcaId\": 1, \"classeProcessual\": [\"DIRETORIA\", \"FAMILIA\"]}"))
				.andExpect(status().is5xxServerError());
	}
}

