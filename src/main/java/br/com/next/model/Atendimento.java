package br.com.next.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "atendimento")
public class Atendimento extends UtilBaseEntities<Long> {

    private static final long serialVersionUID = -7565601921892921834L;

    @OneToOne
    private Senha senha;

    @OneToOne
    private Guiche guiche;

    @SuppressWarnings("unused")
	private Timestamp dataAtendido;

    public Atendimento(Senha senha, Guiche guiche, Timestamp dataAtendido) {
        this.senha = senha;
        this.guiche = guiche;
        this.dataAtendido = dataAtendido;
    }

    public Senha getSenha() {
        return senha;
    }

    public void setSenha(Senha senha) {
        this.senha = senha;
    }

    public Guiche getGuiche() {
        return guiche;
    }

    public void setGuiche(Guiche guiche) {
        this.guiche = guiche;
    }

    public Atendimento() {
        this.dataAtendido = new Timestamp(new Date().getTime());
    }

}
