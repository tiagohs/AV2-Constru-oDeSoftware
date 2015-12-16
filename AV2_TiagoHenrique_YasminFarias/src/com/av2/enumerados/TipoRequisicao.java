package com.av2.enumerados;
/**
 * Representa os tipos de requisições
 * 
 * Enum <code>TipoRequisicao</code>
 *
 * @author tiagohenrique
 * @author yasminfarias
 *
 * @version 1.0 (29/11/2015)
 */
public enum TipoRequisicao {
	AQUISICAO("Aquisição"), CONTRATACAO("Contratação"), ACORDO_LEGAL("Acordo Legal");
	
	/** nome */
	private String nome;
	
	/**
	 * Instancia um novo tipo de requisicao
	 * 
	 * @param _nome
	 * 			nome
	 */
	private TipoRequisicao(String _nome) {
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
