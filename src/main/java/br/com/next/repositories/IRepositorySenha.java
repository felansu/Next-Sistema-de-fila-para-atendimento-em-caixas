package br.com.next.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.next.model.Senha;
import br.com.next.repositories.custom.IRepositorySenhaCustom;

@RepositoryRestResource(collectionResourceRel = "senha", path = "senha")
public interface IRepositorySenha extends CrudRepository<Senha, Long>, IRepositorySenhaCustom {
	
	@Query("SELECT T1 FROM Senha T1 WHERE foiAtendido = false ORDER BY T1.dataEmissao ASC")
	List<Senha> senhasAguardando();
	
	/**
	 * Tipo 0 == Senha comum  ||  Tipo 1 == Senha preferencial
	 */
	@Query("SELECT T1.senha FROM Senha T1 WHERE T1.tipo = '0' AND foiAtendido = false ORDER BY T1.dataEmissao ASC")
    List<Senha> senhasComunsAguardando();
	
	@Query("SELECT T1.senha FROM Senha T1 WHERE T1.tipo = '1' AND foiAtendido = false ORDER BY T1.dataEmissao ASC")
	List<Senha> senhasPreferenciaisAguardando();
	
	@Query("SELECT T1 FROM Senha T1 WHERE T1.id = (SELECT MAX(id) FROM Senha)")
	Senha trazerUltimaSenha();
	
	@Query("SELECT T1 FROM Senha T1 WHERE T1.id = (SELECT MIN(id) FROM Senha WHERE foiAtendido = false)")
	Senha trazerPrimeiraSenha();
	
	@Query("SELECT T1 FROM Senha T1 WHERE T1.id = (SELECT MIN(id) FROM Senha WHERE foiAtendido = false AND tipo = '1')")
	Senha trazerPrimeiraSenhaPreferencial();
	
}