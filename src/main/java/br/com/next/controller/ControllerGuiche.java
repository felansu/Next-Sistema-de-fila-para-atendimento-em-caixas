package br.com.next.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.model.Guiche;
import br.com.next.model.Senha;
import br.com.next.model.enums.EnumGuicheEstados;
import br.com.next.repositories.IRepositorySenha;
import br.com.next.repositories.IrepositoryGuiche;
import br.com.next.service.observer.Acao;

@RestController
@RequestMapping("/Guiche")
public class ControllerGuiche extends ControllerGenerico<Guiche>{
	private static final Logger LOGGER = Logger.getLogger(ControllerGuiche.class);

	@Autowired
    private IrepositoryGuiche repository;
	
	@Autowired
	private IRepositorySenha repositorySenha;
	
    @Override
    protected IrepositoryGuiche getService() {
        return repository;
    }
    
	@RequestMapping(value = "/notificaAlteracao", method = RequestMethod.POST)
	public void notificaAlteracao(@RequestBody Guiche guiche) {
		LOGGER.info("Guiche Nº " + guiche.getNumero() + " " + guiche.getEstado());
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
		Senha senha = repositorySenha.trazerPrimeiraSenha();
		senha.setFoiAtendido(true);
		repositorySenha.save(senha);
		return new ResponseEntity<Senha>(senha, HttpStatus.OK);
	}
}