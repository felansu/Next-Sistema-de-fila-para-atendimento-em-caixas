package br.com.next.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.model.Usuario;
import br.com.next.repositories.IRepositoryUsuario;

@RestController
@RequestMapping("/Usuario")
public class ControllerUsuario extends ControllerGenerico<Usuario> {

	@Autowired
	private IRepositoryUsuario repository;

	@Override
	protected IRepositoryUsuario getService() {
		return repository;
	}

	@RequestMapping(value = "/validarUsuario", method = RequestMethod.POST)
	public ResponseEntity<Usuario> validarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioVerificado = repository.validarUsuario(usuario.getLogin(), usuario.getSenha());
		if (usuarioVerificado != null) {
			return new ResponseEntity<Usuario>(usuarioVerificado, HttpStatus.OK);
		}
		return new ResponseEntity<Usuario>(new Usuario(), HttpStatus.UNAUTHORIZED);
	}

}
