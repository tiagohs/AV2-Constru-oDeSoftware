package com.av2.pool;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.av2.dados.Colaborador;
import com.av2.enumerados.TipoColaborador;
import com.av2.enumerados.TipoDepartamento;
import com.av2.factory.ColaboradoresFactory;
/**
 * Armazena os objetos do tipo colaboradores
 * 
 * Classe <code>PoolColaboradores</code>
 *
 * @author tiagohenrique
 * @author yasminfarias
 *
 * @version 1.0 (29/11/2015)
 */
public class PoolColaboradores {
	private volatile static PoolColaboradores instance = null;
	
	/** pool colaboradores */
	private ArrayList<Colaborador> poolColaboradores;
	
	private PoolColaboradores() {
		poolColaboradores = new ArrayList<Colaborador>();
		
		criarPoolColaboradores();
	}
	
	public static PoolColaboradores getInstance() {

		if (instance == null) {
			synchronized (ColaboradoresFactory.class) {
				if (instance == null) {
					instance = new PoolColaboradores();
				}
			}
		}

		return instance;
	}
	
	/**
	 * Cria os colaboradores e os adiciona no pool de colaboradores
	 */
	private void criarPoolColaboradores() {
		
		
		poolColaboradores.add(new Colaborador(TipoColaborador.CIO, TipoDepartamento.TI, new BigDecimal(1000000), new BigDecimal(1000000), null));
		poolColaboradores.add(new Colaborador(TipoColaborador.CLO, TipoDepartamento.JURIDICO, null, new BigDecimal(500000), new BigDecimal(5000000)));
		poolColaboradores.add(new Colaborador(TipoColaborador.COO, TipoDepartamento.OPERACAO, new BigDecimal(500000), new BigDecimal(500000), null));
		poolColaboradores.add(new Colaborador(TipoColaborador.CHRO, TipoDepartamento.RH, null, new BigDecimal(2000000), null));
		poolColaboradores.add(new Colaborador(TipoColaborador.GERENTE, TipoDepartamento.TI, new BigDecimal(200000), new BigDecimal(200000), null));
		poolColaboradores.add(new Colaborador(TipoColaborador.GERENTE, TipoDepartamento.JURIDICO, null, null, new BigDecimal(250000)));
		poolColaboradores.add(new Colaborador(TipoColaborador.GERENTE, TipoDepartamento.RH, null, new BigDecimal(400000), null));
		poolColaboradores.add(new Colaborador(TipoColaborador.GERENTE, TipoDepartamento.OPERACAO, new BigDecimal(1000000), new BigDecimal(500000), null));
		poolColaboradores.add(new Colaborador(TipoColaborador.SUPERVISOR, TipoDepartamento.TI, new BigDecimal(100000), new BigDecimal(100000), null));
		poolColaboradores.add(new Colaborador(TipoColaborador.SUPERVISOR, TipoDepartamento.OPERACAO, new BigDecimal(250000), new BigDecimal(100000), null));
		poolColaboradores.add(new Colaborador(TipoColaborador.SUPERVISOR, TipoDepartamento.RH, null, new BigDecimal(80000), null));
		poolColaboradores.add(new Colaborador(TipoColaborador.ANALISTA, TipoDepartamento.TI, new BigDecimal(5000), null, null));
		poolColaboradores.add(new Colaborador(TipoColaborador.ANALISTA, TipoDepartamento.JURIDICO, null, null, new BigDecimal(50000)));
		poolColaboradores.add(new Colaborador(TipoColaborador.ANALISTA, TipoDepartamento.OPERACAO, new BigDecimal(50000), new BigDecimal(25000), null));
		poolColaboradores.add(new Colaborador(TipoColaborador.ANALISTA, TipoDepartamento.RH, null, new BigDecimal(15000), null));
	}
	
	/**
	 * Obtem um Colaborador do pool de colaboradores a partir do
	 * tipo do colaborador e do tipo do departamento caso ele exista no pool
	 * 
	 * @param tipoColaborador
	 * 					tipo colaborador
	 * @param tipoDepartamento
	 * 					tipo departamento
	 * @return colaborador
	 */
	public Colaborador getColaborador(TipoColaborador tipoColaborador, TipoDepartamento tipoDepartamento) {
		
		if (poolColaboradores == null) {
			criarPoolColaboradores();
		}
		
		Colaborador colaboradorTemp = new Colaborador(tipoColaborador, tipoDepartamento, null, null, null);
		
		if (poolColaboradores.contains(colaboradorTemp)) {
			Colaborador novoColaborador = poolColaboradores.get(poolColaboradores.indexOf(colaboradorTemp));
			return novoColaborador;
		}
		
		return null;
	}

}
