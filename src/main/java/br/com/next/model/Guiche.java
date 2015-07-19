package br.com.next.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import br.com.next.model.enums.EnumGuicheEstados;
import br.com.next.service.observer.Acao;
import br.com.next.service.observer.IAcaoObserver;

@Entity
@Table(name = "guiche")
public class Guiche extends UtilBaseEntities<Long> implements IAcaoObserver {

	private static final long serialVersionUID = -755168843520292589L;

	private Integer numero;
	private EnumGuicheEstados estado;

	@OneToOne
	@RestResource(exported = false)
	private Usuario usuario;

	@SuppressWarnings("unused")
	private Timestamp dataApertura;

	public Guiche() {
		this.usuario = new Usuario();
		this.dataApertura = new Timestamp(new Date().getTime());
	}

	public Guiche(Integer numero, int estado, Usuario usuario) {
		this.numero = numero;
		this.estado = EnumGuicheEstados.getTipo(estado);
		this.usuario = usuario;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public EnumGuicheEstados getEstado() {
		return estado;
	}

	public void setEstado(EnumGuicheEstados estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public void notificaAlteracao(Acao acao) {
		if (acao.getEstado().equals(EnumGuicheEstados.DISPONIVEL)) {
			System.out.println("Guiche Nº " + this.numero + " " + acao.getEstado());
		}
	}

}