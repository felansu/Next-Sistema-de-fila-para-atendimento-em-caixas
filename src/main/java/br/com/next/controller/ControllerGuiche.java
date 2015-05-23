package br.com.next.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.model.Guiche;
import br.com.next.service.observer.Acao;
import br.com.next.utils.UtilSenhas;

@RestController
@RequestMapping("/Guiche")
public class ControllerGuiche {
	private static final Logger LOGGER = Logger.getLogger(ControllerGuiche.class);

	@RequestMapping(value = "/notificaAlteracao", method = RequestMethod.POST)
	public ResponseEntity<Integer> notificaAlteracao(@RequestBody Guiche guiche) {
		LOGGER.info("Guiche Nº " + guiche.getNumero() + " " + guiche.getEstado());
		Acao acao = new Acao();
		acao.registrarGuiches(guiche);
		acao.setEstado(guiche.getEstado());
		return new ResponseEntity<Integer>(UtilSenhas.obterUltimaSenha(), HttpStatus.OK);
	}
}