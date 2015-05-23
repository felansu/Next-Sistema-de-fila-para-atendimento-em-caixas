package br.com.next.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UtilProperties {
	private static final Logger LOGGER = Logger.getLogger(UtilProperties.class);

	private static Properties properties = new Properties();
	private static OutputStream output = null;

	/**
	 * Métodos referentes ao arquivo guiche.properties
	 */
	private static void carregaGuicheProperties() {
		try {
			properties.load(UtilProperties.class.getClassLoader().getResourceAsStream("guiche.properties"));
		} catch (Exception e) {
			LOGGER.error("Erro ao tentar carregar 'guiche.properties' ", e);
		}
		try {
			output = new FileOutputStream(UtilProperties.class.getClassLoader().getResource("guiche.properties").getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void insereValorGuicheProperties(String chave, String valor) {
		carregaGuicheProperties();
		properties.setProperty(chave, valor);
		try {
			properties.store(output, null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static String obterValorGuicheProperties(String chave) {
		carregaGuicheProperties();
		return properties.getProperty(chave);
	}

	/**
	 * Métodos referentes ao arquivo application.properties
	 */
	private static void carregaApplicationProperties() {
		try {
			properties.load(UtilProperties.class.getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			LOGGER.error("Erro ao tentar carregar 'application.properties' ", e);
		}
	}

	public static String obterValorApplicationProperties(String chave) {
		carregaApplicationProperties();
		return properties.getProperty(chave);
	}
}
