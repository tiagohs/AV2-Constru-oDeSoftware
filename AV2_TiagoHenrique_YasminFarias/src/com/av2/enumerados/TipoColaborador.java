package com.av2.enumerados;
/**
 * Representa os tipos de colaboradores
 * 
 * Enum <code>TipoColaborador</code>
 *
 * @author tiagohenrique
 * @author yasminfarias
 *
 * @version 1.0 (29/11/2015)
 */
public enum TipoColaborador {
	CEO("CEO"), GERENTE("Gerente"), SUPERVISOR("Supervisor"), ANALISTA("Analista"),
	CIO("CIO"), CLO("CLO"), COO("COO"), CHRO("CHRO");
	
	/** nome */
	private String nome;
	
	/**
	 * Instancia um novo tipo de colaborador
	 * 
	 * @param _nome
	 * 			nome
	 */
	private TipoColaborador(String _nome) {
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
