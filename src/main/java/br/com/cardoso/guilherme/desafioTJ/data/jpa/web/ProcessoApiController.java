package br.com.cardoso.guilherme.desafioTJ.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardoso.guilherme.desafioTJ.data.jpa.domain.Processo;
import br.com.cardoso.guilherme.desafioTJ.data.jpa.service.ProcessoService;

@RestController
@RequestMapping("/processos")
public class ProcessoApiController {

	@Autowired
	private ProcessoService processoService;

	@RequestMapping(value = "/{numeroUnico}", method = RequestMethod.GET)
	@ResponseBody
	public Processo obterProcesso(@PathVariable Long numeroUnico) {
		return this.processoService.obterProcesso(numeroUnico);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public Processo criarProcesso(@RequestBody ProcessoForm form) {
		return this.processoService.criarProcesso(form.getComarcaId(), form.getClasseProcessual());
	}
}
