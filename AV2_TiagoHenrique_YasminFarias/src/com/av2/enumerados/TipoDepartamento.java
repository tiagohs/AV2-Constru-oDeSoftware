package com.av2.enumerados;
/**
 * Representa os tipos de departamentos
 * 
 * Enum <code>TipoDepartamento</code>
 *
 * @author tiagohenrique
 * @author yasminfarias
 *
 * @version 1.0 (29/11/2015)
 */
public enum TipoDepartamento {
	OPERACAO("Operação"), TI("TI"), RH("RH"), JURIDICO("Juridico"), PRESIDENTE("Presidente");
	
	/** nome */
	private String nome;
	
	/**
	 * Instancia um novo tipo de departamento
	 * 
	 * @param _nome
	 * 			nome
	 */
	private TipoDepartamento(String _nome) {
		nome = _nome;
	}
	
	/**
	 * Obtem nome
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
}