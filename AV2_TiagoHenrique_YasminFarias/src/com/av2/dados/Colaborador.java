package com.av2.dados;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.av2.enumerados.TipoColaborador;
import com.av2.enumerados.TipoDepartamento;
import com.av2.enumerados.TipoRequisicao;
import com.av2.factory.ColaboradoresFactory;

/**
 * Representa um Colaborador
 * 
 * Classe <code>Colaborador</code>
 *
 * @author tiagohenrique
 * @author yasminfarias
 *
 * @version 1.0 (29/11/2015)
 */
public class Colaborador {
	
	/** Constante MENOR */
	public static final int MENOR = -1;
	
	/** Constante IGUAL */
	public static final int IGUAL = 0;
	
	/** Constante MAIOR */
	public static final int MAIOR = 1;
	
	/**  tipo colaborador */
	private TipoColaborador tipoColaborador;
	
	/** tipo departamento */
	private TipoDepartamento tipoDepartamento;
	
	/** lista orcamento maximo */
	private Map<TipoRequisicao, BigDecimal> listaOrcamentoMaximo;
	
	/** orcamento */
	private Orcamento orcamento;

	/** proximo colaborador */
	private Colaborador proximo;

	/**
	 * Instancia um novo Colaborador
	 * 
	 * @param _tipoColaborador
	 * 					tipo colaborador
	 * @param _tipoDepartamento
	 * 					tipo departamento
	 * @param maximoOrcamentoAquisicao
	 * 					orcamento maximo aquisicao
	 * @param maximoOrcamentoContratacao
	 * 					orcamento maximo contratacao
	 * @param maximoOrcamentoAcordoLegal
	 * 					orcamento maximo acordo legal
	 */
	public Colaborador(TipoColaborador _tipoColaborador, TipoDepartamento _tipoDepartamento,
			BigDecimal maximoOrcamentoAquisicao, BigDecimal maximoOrcamentoContratacao,
			BigDecimal maximoOrcamentoAcordoLegal) {
		tipoColaborador = _tipoColaborador;
		tipoDepartamento = _tipoDepartamento;
		orcamento = Orcamento.getInstance();
		
		listaOrcamentoMaximo = new HashMap<TipoRequisicao, BigDecimal>();
		
		adicionarLimitesOrcamentais(maximoOrcamentoAquisicao, maximoOrcamentoContratacao, maximoOrcamentoAcordoLegal);
	}
	
	private void adicionarLimitesOrcamentais(BigDecimal maximoOrcamentoAquisicao, BigDecimal maximoOrcamentoContratacao, BigDecimal maximoOrcamentoAcordoLegal) {
		
		if (maximoOrcamentoAquisicao != null) {
			listaOrcamentoMaximo.put(TipoRequisicao.AQUISICAO, maximoOrcamentoAquisicao);
		}
		
		if (maximoOrcamentoContratacao != null) {
			listaOrcamentoMaximo.put(TipoRequisicao.CONTRATACAO, maximoOrcamentoContratacao);
		}
		
		if (maximoOrcamentoAcordoLegal != null) {
			listaOrcamentoMaximo.put(TipoRequisicao.ACORDO_LEGAL, maximoOrcamentoAcordoLegal);
		}
	}

	/**
	 * Verifica o orcamento de seu departamento.
	 * Se estiver dentro do orcamento, verifica se ele é 
	 * permitido para fazer e requisicao. Caso seja, ele a realiza.
	 * Caso contrário, passa a requisicao para o proximo.
	 * 
	 * @param requisicao
	 * 				requisicao
	 */
	public void aprovarRequisicao(Requisicao requisicao) {
		
		if (orcamento.verificaOrcamento(requisicao.getValorRequisicao(), tipoDepartamento)) {
			if (isPermitidoRealizarPorOrcamento(requisicao)) {
				realizarRequisicao(requisicao);
			} else {
				proximo.aprovarRequisicao(requisicao);
			}
		} else {
			System.out.println("Não há Orçamento Suficiente no Departamendo de " + tipoDepartamento.getNome() + ", Saldo: " + orcamento.getOrcamento(tipoDepartamento));
		}
	}
	
	/**
	 * Se O Valor da Operação For menor que o Máximo permitido, realiza a Requisição.
	 * 
	 * @param requisicao
	 * 				requisicao
	 * @return boolean
	 */
	private boolean isPermitidoRealizarPorOrcamento(Requisicao requisicao) {
		BigDecimal valorMaximoOrcamento = listaOrcamentoMaximo.get(requisicao.getTipoRequisicao());
		
		//Não encontrou nenhum Valor Maximo, ou seja, ele não pode realizar tal requisicao.
		if (valorMaximoOrcamento == null) {
			return false;
		}
		
		if (tipoDepartamento == TipoDepartamento.PRESIDENTE) {
			return true;
		}else if (requisicao.getValorRequisicao().compareTo(valorMaximoOrcamento) == MENOR) {
			return true;
		} else if (requisicao.getValorRequisicao().compareTo(valorMaximoOrcamento) == IGUAL) {
			return true;
		} else if (requisicao.getValorRequisicao().compareTo(valorMaximoOrcamento) == MAIOR) {
			return false;
		}
		
		return false;
	}
	
	/**
	 * Realiza A requisicao, caso esteja no Colaborador Correto.
	 * 
	 * @param requisicao
	 * 				requisicao
	 */
	public void realizarRequisicao(Requisicao requisicao) {
		
		System.out.println("Departamento: " + tipoDepartamento.getNome());
		System.out.println("Colaborador: " + tipoColaborador.getNome());
		System.out.println("\nDados da Requisição: \n\n" + requisicao.toString());
		
		if (tipoDepartamento == TipoDepartamento.PRESIDENTE) {
			System.out.println("Requisicao Realizada Pelo CEO.");
		} else {
			System.out.println("Orçamento: R$ " + orcamento.getOrcamento(tipoDepartamento));
			orcamento.abaterValorOrcamento(requisicao.getValorRequisicao(), tipoDepartamento);
			System.out.println("Orçamento após a Requisicão: R$ " + orcamento.getOrcamento(tipoDepartamento));
		}
	}
	
	/**
	 * Define proximo colaborador
	 * 
	 * @param proximo
	 * 				proximo colaborador
	 */
	public void setProximo(Colaborador proximo) {
		this.proximo = proximo;
	}

	/**
	 * Obtem tipo colaborador
	 * 
	 * @return tipo colaborador
	 */
	public TipoColaborador getTipoColaborador() {
		return tipoColaborador;
	}

	/**
	 * Define tipo colaborador
	 * 
	 * @param tipoColaborador
	 * 					tipo colaborador
	 */
	public void setTipoColaborador(TipoColaborador tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}

	/**
	 * Obtem tipo departamento
	 * 
	 * @return tipo departamento
	 */
	public TipoDepartamento getTipoDepartamento() {
		return tipoDepartamento;
	}

	/**
	 * Defina tipo departamento
	 * 
	 * @param tipoDepartamento
	 * 					tipo departamento
	 */
	public void setTipoDepartamento(TipoDepartamento tipoDepartamento) {
		this.tipoDepartamento = tipoDepartamento;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Colaborador) {
			Colaborador colaborador = (Colaborador) obj;
			return (getTipoDepartamento() == colaborador.getTipoDepartamento()
					&& getTipoColaborador() == colaborador.getTipoColaborador());
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Colaborador: %s\nDepartamento: %s\n", tipoColaborador.getNome(), tipoDepartamento.getNome());
	}
}