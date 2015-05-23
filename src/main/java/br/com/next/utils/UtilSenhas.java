package br.com.next.utils;

public class UtilSenhas {
	
	private static final String CHAVE_ULTIMA_SENHA = "ultima.senha";
	
	public static int obterEIncrementaSenha() {
		int ultimaSenha = Integer.valueOf(UtilProperties.obterValorGuicheProperties(CHAVE_ULTIMA_SENHA));
		ultimaSenha++;
		UtilProperties.insereValorGuicheProperties(CHAVE_ULTIMA_SENHA, String.valueOf(ultimaSenha));
		return ultimaSenha;
	}
	
	public static Integer obterUltimaSenha() {
		return Integer.valueOf(UtilProperties.obterValorGuicheProperties(CHAVE_ULTIMA_SENHA));
	}
}
