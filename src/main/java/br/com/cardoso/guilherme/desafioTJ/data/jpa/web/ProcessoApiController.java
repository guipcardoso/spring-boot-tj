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
@RequestMapping("/processo")
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
