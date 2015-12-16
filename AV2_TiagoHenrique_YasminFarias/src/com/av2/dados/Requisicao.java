package com.av2.dados;
import java.math.BigDecimal;

import com.av2.enumerados.TipoDepartamento;
import com.av2.enumerados.TipoRequisicao;
/**
 * Representa uma Requisição
 * 
 * Classe <code>Requisicao</code>
 *
 * @author tiagohenrique
 * @author yasminfarias
 *
 * @version 1.0 (29/11/2015)
 */
public class Requisicao {
	
	/** tipo requisicao */
	private TipoRequisicao tipoRequisicao;
	
	/** tipo departamento */
	private TipoDepartamento tipoDepartamento;
	
	/** valor requisicao */
	private BigDecimal valorRequisicao;
	
	/**
	 * Instancia uma nova requisicao
	 * 
	 * @param _tipoRequisicao
	 * 				tipo requisicao
	 * @param _tipoDepartamento
	 * 				tipo departamento
	 * @param _valor
	 * 				valor requisicao
	 */
	public Requisicao(TipoRequisicao _tipoRequisicao, TipoDepartamento _tipoDepartamento, BigDecimal _valor) {
		tipoRequisicao = _tipoRequisicao;
		tipoDepartamento = _tipoDepartamento;
		valorRequisicao = _valor;
	}
	
	/**
	 * Obtem tipo requisicao
	 * 
	 * @return tipo requisicao
	 */
	public TipoRequisicao getTipoRequisicao() {
		return tipoRequisicao;
	}
	
	/**
	 * Define tipo requisicao
	 * 
	 * @param tipoRequisicao
	 * 				tipo requisicao
	 */
	public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
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
	 * Define tipo departamento
	 * 
	 * @param tipoDepartamento
	 * 				tipo departamento
	 */
	public void setTipoDepartamento(TipoDepartamento tipoDepartamento) {
		this.tipoDepartamento = tipoDepartamento;
	}
	
	/**
	 * Obtem valor requisicao
	 * 
	 * @return valor requisicao
	 */
	public BigDecimal getValorRequisicao() {
		return valorRequisicao;
	}
	
	/**
	 * Define  valor requisicao
	 * 
	 * @param valorRequisicao
	 * 				 valor requisicao
	 */
	public void setValorRequisicao(BigDecimal valorRequisicao) {
		this.valorRequisicao = valorRequisicao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Tipo de Requisicao: %s\nDepartamento: %s\nValor da Requisição: R$ %.2f\n", tipoRequisicao.getNome(), tipoDepartamento.getNome(), getValorRequisicao());
	}
}
