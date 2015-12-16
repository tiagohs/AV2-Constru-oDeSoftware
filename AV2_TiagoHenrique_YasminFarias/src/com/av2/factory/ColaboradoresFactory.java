package com.av2.factory;

import com.av2.dados.Colaborador;
import com.av2.enumerados.TipoColaborador;
import com.av2.enumerados.TipoDepartamento;
import com.av2.enumerados.TipoRequisicao;
import com.av2.pool.PoolColaboradores;
/**
 * Representa uma fábrica de colaboradores
 * 
 * Classe <code>ColaboradoresFactory</code>
 *
 * @author tiagohenrique
 * @author yasminfarias
 *
 * @version 1.0 (29/11/2015)
 */
public class ColaboradoresFactory {
	/** instancia */
	private volatile static ColaboradoresFactory instance = null;

	/** pool colaboradores */
	private PoolColaboradores poolColaboradores;

	/**
	 * Intancia a Factory
	 */
	private ColaboradoresFactory() {
		poolColaboradores = PoolColaboradores.getInstance();
	}

	/**
	 * Obtam a instancia da classe
	 * Aplica-se o padrão Singleton para única instanciação
	 * 
	 * @return instancia
	 */
	public static ColaboradoresFactory getInstance() {

		if (instance == null) {
			synchronized (ColaboradoresFactory.class) {
				if (instance == null) {
					instance = new ColaboradoresFactory();
				}
			}
		}

		return instance;
	}

	/**
	 * Obtem o colaborador que pode fazer essa requisição.
	 * A partir do tipo da requisicao, vê no departamento 
	 * qual colaborador poderá fazer a requisicao a partir 
	 * do departamento passado. 
	 * 
	 * @param tipoDepartamento
	 * 					tipo departamento
	 * @param tipoRequisicao
	 * 					tipo requisicao
	 * @return Colaborador
	 */
	public Colaborador getColaborador(TipoDepartamento tipoDepartamento, TipoRequisicao tipoRequisicao) {
		
		switch (tipoRequisicao) {
			case ACORDO_LEGAL:
				return this.tipoDepartamentoAcordosLegais(tipoDepartamento);
			case AQUISICAO:
				return this.tipoDepartamentoAquisicao(tipoDepartamento);
			case CONTRATACAO:
				return this.tipoDepartamentoContratacao(tipoDepartamento);
			default:
				return null;
		}
		
	}
	
	/**
	 * De Acordo com o Departamento, Jogará pro Lado ou Pra cima a Requisicao
	 * 
	 * @param tipoDepartamento
	 * 					tipo departamento
	 * @return Colaborador
	 */
	private Colaborador tipoDepartamentoAcordosLegais(TipoDepartamento tipoDepartamento) {
		
		if (tipoDepartamento == TipoDepartamento.TI || tipoDepartamento == TipoDepartamento.OPERACAO || tipoDepartamento == TipoDepartamento.RH) {
			System.out.println("Não Realiza, Joga para o Juridico.");
			return chainAcordoLegalJuridico();
		} else if (tipoDepartamento == TipoDepartamento.JURIDICO){
			return chainAcordoLegalJuridico();
		}
		
		return null;
	}
	
	/**
	 * De Acordo com o Departamento, Jogará pro Lado ou Pra cima a Requisicao
	 * 
	 * @param tipoDepartamento
	 * 					tipo departamento
	 * @return Colaborador
	 */
	private Colaborador tipoDepartamentoAquisicao(TipoDepartamento tipoDepartamento) {
		
		if (tipoDepartamento == TipoDepartamento.JURIDICO || tipoDepartamento == TipoDepartamento.RH) {
			System.out.println("Não Realiza, Joga para Operações.");
			return chainAquisicaoOperacoes();
		} else if (tipoDepartamento == TipoDepartamento.TI) {
			return chainAquisicaoTI();
		} else if (tipoDepartamento == TipoDepartamento.OPERACAO) {
			return chainAquisicaoOperacoes();
		}
		
		return null;
	}
	
	/**
	 * De Acordo com o Departamento, Jogará pro Lado ou Pra cima a Requisicao
	 * 
	 * @param tipoDepartamento
	 * 					tipo departamento
	 * @return Colaborador
	 */
	private Colaborador tipoDepartamentoContratacao(TipoDepartamento tipoDepartamento) {
		
		if (tipoDepartamento == TipoDepartamento.JURIDICO || tipoDepartamento == TipoDepartamento.TI) {
			System.out.println("Não Realiza, Joga para Operações.");
			return chainContratacaoOperacao();
		} else if (tipoDepartamento == TipoDepartamento.OPERACAO) {
			return chainContratacaoOperacao();
		} else if (tipoDepartamento == TipoDepartamento.RH) {
			return chainContratacaoRH();
		}
		
		return null;
	}
	
	/**
	 * Instancia os cargos referentes a RH
	 * Obtem-se o colaborador de menor cargo
	 * Aplica-se o padrão Chain
	 * 
	 * @return supervisor RH
	 */
	private Colaborador chainContratacaoRH() {
		
		Colaborador analistaRH = poolColaboradores.getColaborador(TipoColaborador.ANALISTA, TipoDepartamento.RH);
		Colaborador supervisorRH = poolColaboradores.getColaborador(TipoColaborador.SUPERVISOR, TipoDepartamento.RH);
		Colaborador gerenteRH = poolColaboradores.getColaborador(TipoColaborador.GERENTE, TipoDepartamento.RH);
		Colaborador CHRO = poolColaboradores.getColaborador(TipoColaborador.CHRO, TipoDepartamento.RH);
		Colaborador CEO = poolColaboradores.getColaborador(TipoColaborador.CEO, TipoDepartamento.PRESIDENTE);
		
		analistaRH.setProximo(supervisorRH);
		supervisorRH.setProximo(gerenteRH);
		gerenteRH.setProximo(CHRO);
		CHRO.setProximo(CEO);
		
		return analistaRH;
	}

	/**
	 * Instancia os cargos referentes a operacoes de contratacao
	 * Obtem-se o colaborador de menor cargo
	 * Aplica-se o padrão Chain
	 * 
	 * @return supervisor operacao
	 */
	private Colaborador chainContratacaoOperacao() {
		
		Colaborador analistaOperacao = poolColaboradores.getColaborador(TipoColaborador.ANALISTA, TipoDepartamento.OPERACAO);
		Colaborador supervisorOperacao = poolColaboradores.getColaborador(TipoColaborador.SUPERVISOR, TipoDepartamento.OPERACAO);
		Colaborador gerenteOperacao = poolColaboradores.getColaborador(TipoColaborador.GERENTE, TipoDepartamento.OPERACAO);
		Colaborador COO = poolColaboradores.getColaborador(TipoColaborador.COO, TipoDepartamento.OPERACAO);
		Colaborador CEO = poolColaboradores.getColaborador(TipoColaborador.CEO, TipoDepartamento.OPERACAO);
		
		analistaOperacao.setProximo(supervisorOperacao);
		supervisorOperacao.setProximo(gerenteOperacao);
		gerenteOperacao.setProximo(COO);
		COO.setProximo(CEO);
		
		return analistaOperacao;
	}

	/**
	 * Instancia os cargos referentes a operacoes de aquisicao 
	 * Obtem-se o colaborador de menor cargo
	 * Aplica-se o padrão Chain
	 * 
	 * @return gerente operacoes
	 */
	private Colaborador chainAquisicaoOperacoes() {
		
		Colaborador analistaOperacoes = poolColaboradores.getColaborador(TipoColaborador.ANALISTA, TipoDepartamento.OPERACAO);
		Colaborador gerenteOperacoes = poolColaboradores.getColaborador(TipoColaborador.SUPERVISOR, TipoDepartamento.OPERACAO);
		Colaborador COO = poolColaboradores.getColaborador(TipoColaborador.COO, TipoDepartamento.OPERACAO);
		Colaborador CEO = poolColaboradores.getColaborador(TipoColaborador.CEO, TipoDepartamento.OPERACAO);
		
		analistaOperacoes.setProximo(gerenteOperacoes);
		gerenteOperacoes.setProximo(COO);
		COO.setProximo(CEO);
		
		return analistaOperacoes;
	}

	/**
	 * Instancia os cargos referentes a aquisicoes de TI
	 * Obtem-se o colaborador de menor cargo
	 * Aplica-se o padrão Chain
	 * 
	 * @return gerente TI
	 */
	private Colaborador chainAquisicaoTI() {
		
		Colaborador analistaTI = poolColaboradores.getColaborador(TipoColaborador.ANALISTA, TipoDepartamento.TI);
		Colaborador gerenteTI = poolColaboradores.getColaborador(TipoColaborador.SUPERVISOR, TipoDepartamento.TI);
		Colaborador CIO = poolColaboradores.getColaborador(TipoColaborador.CIO, TipoDepartamento.TI);
		Colaborador CEO = poolColaboradores.getColaborador(TipoColaborador.CEO, TipoDepartamento.PRESIDENTE);
		
		analistaTI.setProximo(gerenteTI);
		gerenteTI.setProximo(CIO);
		CIO.setProximo(CEO);
		
		return analistaTI;
	}

	/**
	 * Instancia os cargos referentes ao juridico da empresa
	 * Obtem-se o colaborador de menor cargo
	 * Aplica-se o padrão Chain
	 * 
	 * @return gerente juridico
	 */
	private Colaborador chainAcordoLegalJuridico() {
		
		Colaborador analistaAnalista = poolColaboradores.getColaborador(TipoColaborador.ANALISTA, TipoDepartamento.JURIDICO);
		Colaborador gerenteJuridico = poolColaboradores.getColaborador(TipoColaborador.GERENTE, TipoDepartamento.JURIDICO);
		Colaborador CLO = poolColaboradores.getColaborador(TipoColaborador.CLO, TipoDepartamento.JURIDICO);
		Colaborador CEO = poolColaboradores.getColaborador(TipoColaborador.CEO, TipoDepartamento.PRESIDENTE);
		
		analistaAnalista.setProximo(gerenteJuridico);
		gerenteJuridico.setProximo(CLO);
		CLO.setProximo(CEO);
		
		return analistaAnalista;
	}

}
