package br.com.next.model;

import java.io.Serializable;

import br.com.next.model.enums.EnumGuicheEstados;
import br.com.next.service.observer.Acao;
import br.com.next.service.observer.IAcaoObserver;
import br.com.next.utils.UtilSenhas;

public class Guiche implements Serializable, IAcaoObserver {

	private static final long serialVersionUID = -755168843520292589L;

	private Integer numero;
	private EnumGuicheEstados estado;

	public Guiche() {
	}

	public Guiche(Integer numero, int estado) {
		this.numero = numero;
		this.estado = EnumGuicheEstados.getTipo(estado);
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

	@Override
	public void notificaAlteracao(Acao acao) {
		if (acao.getEstado().equals(EnumGuicheEstados.DISPONIVEL)) {
//			UtilDorme.dorme2();
			System.out.println("Guiche Nº " + this.numero + " " + acao.getEstado() + "\nSenha: " + UtilSenhas.obterEIncrementaSenha());
		}
	}

}