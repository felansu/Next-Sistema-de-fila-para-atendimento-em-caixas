package br.com.next.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.model.Senha;
import br.com.next.model.enums.EnumTipoSenha;
import br.com.next.repositories.IRepositorySenha;

@RestController
@RequestMapping("/Senha")
public class ControllerSenha extends ControllerGenerico<Senha> {

	@Autowired
	private IRepositorySenha repository;

	@Override
	protected IRepositorySenha getService() {
		return repository;
	}

	/**
	 * Methods: solicitarSenhaComum - solicitarSenhaPreferencial
	 * 
	 * Verifica se tem alguma senha, se tiver pega o último número e cadastra
	 * uma nova senha + 1 Caso não tiver (é a primeira senha) cadastra ela com o
	 * número 1
	 * 
	 * @return última senha
	 */
	@RequestMapping(value = "/solicitarSenhaComum", method = RequestMethod.GET)
	public ResponseEntity<Senha> solicitarSenhaComum() {
		Senha ultimaSenha = repository.trazerUltimaSenha();
		Senha novaSenha = new Senha();
		if (ultimaSenha != null) {
			novaSenha.setSenha(ultimaSenha.getSenha() + 1);
		} else {
			novaSenha.setSenha(1);
		}
		novaSenha.setTipo(EnumTipoSenha.COMUM);
		this.repository.save(novaSenha);
		return new ResponseEntity<Senha>(novaSenha, HttpStatus.OK);
	}

	@RequestMapping(value = "/solicitarSenhaPreferencial", method = RequestMethod.GET)
	public ResponseEntity<Senha> solicitarSenhaPreferencial() {
		Senha ultimaSenha = repository.trazerUltimaSenha();
		Senha novaSenha = new Senha();
		if (ultimaSenha != null) {
			novaSenha.setSenha(ultimaSenha.getSenha() + 1);
		} else {
			novaSenha.setSenha(1);
		}
		novaSenha.setTipo(EnumTipoSenha.PREFERENCIAL);
		this.repository.save(novaSenha);
		return new ResponseEntity<Senha>(novaSenha, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/senhasAguardando", method = RequestMethod.GET)
	public ResponseEntity<List<Senha>> senhasAguardando() {
		return new ResponseEntity<List<Senha>>(repository.senhasAguardando(), HttpStatus.OK);
	}
}