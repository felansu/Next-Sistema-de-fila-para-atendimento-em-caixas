package br.com.next.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.next.model.enums.EnumTipoSenha;

@Entity
@Table(name = "senha")
public class Senha extends UtilBaseEntities<Long> {
	private static final long serialVersionUID = 4913189972402868651L;

	private int senha;
	private EnumTipoSenha tipo;
	private Timestamp dataEmissao;
	private boolean foiAtendido;

	public Senha() {
		this.senha = 0;
		this.dataEmissao = new Timestamp(new Date().getTime());
	}

	public Senha(int senha, EnumTipoSenha tipo, Timestamp dataEmissao, boolean foiAtendido) {
		this.senha = senha;
		this.tipo = tipo;
		this.dataEmissao = dataEmissao;
		this.foiAtendido = foiAtendido;
	}

	public int getSenha() {
		return senha;
	}

	public EnumTipoSenha getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoSenha tipo) {
		this.tipo = tipo;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public Timestamp getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Timestamp dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public boolean isFoiAtendido() {
		return foiAtendido;
	}

	public void setFoiAtendido(boolean foiAtendido) {
		this.foiAtendido = foiAtendido;
	}
}
