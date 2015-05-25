package br.com.next.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.model.Atendimento;
import br.com.next.model.Guiche;
import br.com.next.model.Senha;
import br.com.next.model.enums.EnumGuicheEstados;
import br.com.next.repositories.IRepositoryAtendimento;
import br.com.next.repositories.IRepositoryGuiche;
import br.com.next.repositories.IRepositorySenha;
import br.com.next.service.observer.Acao;

@RestController
@RequestMapping("/Guiche")
public class ControllerGuiche extends ControllerGenerico<Guiche> {

	@Autowired
	private IRepositoryGuiche repository;

	@Autowired
	private IRepositorySenha repositorySenha;

	@Autowired
	private IRepositoryAtendimento repositoryAtendimento;

	@Override
	protected IRepositoryGuiche getService() {
		return repository;
	}

	@RequestMapping(value = "/notificaAlteracao", method = RequestMethod.POST)
	public void notificaAlteracao(@RequestBody Guiche guiche) {
		Acao acao = new Acao();
		acao.registrarGuiches(guiche);
		acao.setEstado(guiche.getEstado());
	}

	@RequestMapping(value = "/registrarGuiche", method = RequestMethod.POST)
	public ResponseEntity<Guiche> registrarGuiche(@RequestBody Guiche guiche) {
		guiche.setEstado(EnumGuicheEstados.FECHADO);
		repository.save(guiche);
		Acao acao = new Acao();
		acao.registrarGuiches(guiche);
		acao.setEstado(guiche.getEstado());
		return new ResponseEntity<Guiche>(guiche, HttpStatus.OK);
	}

	@RequestMapping(value = "/chamaProximaSenha", method = RequestMethod.POST)
	public ResponseEntity<Senha> chamaProximaSenha(@RequestBody Guiche guiche) {
		Senha senha = trazSenhaPreferencialOuComum();
		if (senha == null) {
			return new ResponseEntity<Senha>(senha, HttpStatus.PRECONDITION_REQUIRED);
		}
		senha.setFoiAtendido(true);
		Senha senhaSalva = repositorySenha.save(senha);
		Atendimento atendimento = new Atendimento();
		atendimento.setGuiche(guiche);
		atendimento.setSenha(senha);
		repositoryAtendimento.save(atendimento);
		senha = new Senha();
		atendimento = new Atendimento();
		return new ResponseEntity<Senha>(senhaSalva, HttpStatus.OK);
	}

	private Senha trazSenhaPreferencialOuComum() {
		Senha senha = repositorySenha.trazerPrimeiraSenhaPreferencial();
		if (senha == null) {
			senha = repositorySenha.trazerPrimeiraSenha();
		}
		return senha;
	}
}