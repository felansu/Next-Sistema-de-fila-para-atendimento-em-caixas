package br.com.next.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.next.model.Usuario;
import br.com.next.repositories.custom.IRepositorySenhaCustom;

@RepositoryRestResource(collectionResourceRel = "usuario", path = "usuario")
public interface IRepositoryUsuario extends CrudRepository<Usuario, Long>, IRepositorySenhaCustom {
	
	@Query("SELECT T1 FROM Usuario T1 WHERE T1.login = ?1 AND T1.senha = ?2")
	Usuario validarUsuario(String login, String senha);
	
}
