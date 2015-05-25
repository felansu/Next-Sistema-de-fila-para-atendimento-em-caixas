package br.com.next.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.next.model.Guiche;
import br.com.next.repositories.custom.IRepositorySenhaCustom;

@RepositoryRestResource(collectionResourceRel = "guiche", path = "guiche")
public interface IRepositoryGuiche extends CrudRepository<Guiche, Long>, IRepositorySenhaCustom {
	
}