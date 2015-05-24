package br.com.next.utils.emissorSenhas;

import br.com.next.utils.UtilSenhas;

public class EmissorSenhasComun implements IEmissorSenhas{

	@Override
	public Integer gerarSenha() {
		return UtilSenhas.obterEIncrementaSenhaComum();
	}

}
