package br.com.next.utils.emissorSenhas;

import br.com.next.utils.UtilSenhas;

public class EmissorSenhasPreferencial implements IEmissorSenhas{

	@Override
	public Integer gerarSenha() {
		return UtilSenhas.obterEIncrementaSenhaPreferencial();
	}

}
