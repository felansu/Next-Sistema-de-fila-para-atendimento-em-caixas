package br.com.next.utils;

public class UtilSenhas {
	
	private static final String CHAVE_ULTIMA_SENHA = "ultima.senha";
	private static final String CHAVE_ULTIMA_SENHA_PREFERENCIAL = "ultima.senha.preferencial";
	
	public static int obterEIncrementaSenhaComum() {
		int ultimaSenha = Integer.valueOf(UtilProperties.obterValorGuicheProperties(CHAVE_ULTIMA_SENHA));
		ultimaSenha++;
		UtilProperties.insereValorGuicheProperties(CHAVE_ULTIMA_SENHA, String.valueOf(ultimaSenha));
		return ultimaSenha;
	}
	
	public static int obterEIncrementaSenhaPreferencial() {
		int ultimaSenha = Integer.valueOf(UtilProperties.obterValorGuicheProperties(CHAVE_ULTIMA_SENHA_PREFERENCIAL));
		ultimaSenha++;
		UtilProperties.insereValorGuicheProperties(CHAVE_ULTIMA_SENHA_PREFERENCIAL, String.valueOf(ultimaSenha));
		return ultimaSenha;
	}
	
	public static Integer obterUltimaSenhaComum() {
		return Integer.valueOf(UtilProperties.obterValorGuicheProperties(CHAVE_ULTIMA_SENHA));
	}
	
	public static Integer obterUltimaSenhaPreferencial() {
		return Integer.valueOf(UtilProperties.obterValorGuicheProperties(CHAVE_ULTIMA_SENHA_PREFERENCIAL));
	}
}
