package br.com.next.service.observer;

import java.util.HashSet;
import java.util.Set;

import br.com.next.model.enums.EnumGuicheEstados;

public class Acao {

	private EnumGuicheEstados estado;
	private Set<IAcaoObserver> guiches = new HashSet<IAcaoObserver>();

	
	public Acao() {
		this.estado = EnumGuicheEstados.FECHADO;
	}

	public Acao(EnumGuicheEstados estado) {
		this.estado = estado;
	}

	public void registrarGuiches(IAcaoObserver guiche) {
		this.guiches.add(guiche);
	}

	public void cancelarGuiche(IAcaoObserver guiche) {
		this.guiches.remove(guiche);
	}

	public EnumGuicheEstados getEstado() {
		return estado;
	}

	public void setEstado(EnumGuicheEstados estado) {
		this.estado = estado;
		for (IAcaoObserver guiche : guiches) {
			guiche.notificaAlteracao(this);
		}
	}

	public Set<IAcaoObserver> getGuiches() {
		return guiches;
	}
}