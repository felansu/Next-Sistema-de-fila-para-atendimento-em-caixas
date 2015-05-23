package br.com.next.utils.emissorSenhas;

public class EmissorSenhasPreferencial implements IEmissorSenhas{

	@Override
	public void gerarSenha() {
		System.out.println("Emitindo senha comum 123");
	}

}
