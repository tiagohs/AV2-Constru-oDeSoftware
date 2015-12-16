package com.av2.dados;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.av2.enumerados.TipoDepartamento;
/**
 * Representa um Orçamento
 * 
 * Classe <code>Orcamento</code>
 *
 * @author tiagohenrique
 * @author yasminfarias
 *
 * @version 1.0 (29/11/2015)
 */
public class Orcamento {
	
	/** instancia */
	private volatile static Orcamento instance = null;
	
	/** lista orcamentos */
	private Map<TipoDepartamento, BigDecimal> listaOrcamentos;
	
	/**
	 * Instancia um novo orcamento
	 */
	private Orcamento() {
		listaOrcamentos = new HashMap<TipoDepartamento, BigDecimal>();
		
		addAllOrcamentos();
	}
	
	/**
	 * Inicializa a lista com os orçamentos maximos de cada departamento
	 */
	private void addAllOrcamentos() {
		
		listaOrcamentos.put(TipoDepartamento.PRESIDENTE, new BigDecimal(0));
		listaOrcamentos.put(TipoDepartamento.TI, new BigDecimal(2500000));
		listaOrcamentos.put(TipoDepartamento.RH, new BigDecimal(2000000));
		listaOrcamentos.put(TipoDepartamento.JURIDICO, new BigDecimal(5000000));
		listaOrcamentos.put(TipoDepartamento.OPERACAO, new BigDecimal(50000000));
	}
	
	/**
	 * Obtem a instancia de Orçamento, faz o padrão Singleton
	 * 
	 * @return Orcamento
	 */
	public static Orcamento getInstance() {
		
		if (instance == null) {
			synchronized (Orcamento.class) {
				if (instance == null) {
					instance = new Orcamento();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * Verifica se o valor da requisicao esta dentro do orcamento total do departamento
	 * 
	 * @param valorDaRequisicao
	 * 				valor requisicao
	 * @param tipoDepartamento
	 * 				tipo departamento
	 * 
	 * @return boolean
	 */
	public boolean verificaOrcamento(BigDecimal valorDaRequisicao, TipoDepartamento tipoDepartamento) {
		
		BigDecimal orcamento = getOrcamento(tipoDepartamento);
		
		if (tipoDepartamento == TipoDepartamento.PRESIDENTE) {
			return true;
		} else if (valorDaRequisicao.compareTo(orcamento) == Colaborador.MENOR) {
			return true;
		} else if (valorDaRequisicao.compareTo(orcamento) == Colaborador.IGUAL) {
			return true;
		} else if (valorDaRequisicao.compareTo(orcamento) == Colaborador.MAIOR) {
			return false;
		}
		
		return false;
	}
	
	/**
	 * Subtrai o valor total de requisicoes do departamento 
	 * dado pelo valor da requisicao atual.
	 * 
	 * @param valorRequisicao
	 * 				valor requisicao
	 * @param tipoDepartamento
	 * 				tipo departamento
	 */
	public void abaterValorOrcamento(BigDecimal valorRequisicao, TipoDepartamento tipoDepartamento) {
		
		BigDecimal novoOrcamento = getOrcamento(tipoDepartamento).subtract(valorRequisicao);
		listaOrcamentos.replace(tipoDepartamento, novoOrcamento);
	}
	
	/**
	 * Obtem orcamento a partir de um departamento
	 * 
	 * @param tipoDepartamento
	 * 				tipo orcamento
	 * 
	 * @return orcamento
	 */
	public BigDecimal getOrcamento(TipoDepartamento tipoDepartamento) {
		return listaOrcamentos.get(tipoDepartamento);
	}
	
	/**
	 * Lista todos os orçamentos de cada departamento
	 * 
	 * @return Lista Orcamentos
	 */
	public Map<TipoDepartamento, BigDecimal> getListaOrcamentos() {
		return listaOrcamentos;
	}
	
	/**
	 * Define Lista Orcamentos
	 * 
	 * @param listaOrcamentos
	 * 				lista orcamento
	 */
	public void setListaOrcamentos(Map<TipoDepartamento, BigDecimal> listaOrcamentos) {
		this.listaOrcamentos = listaOrcamentos;
	}
}
