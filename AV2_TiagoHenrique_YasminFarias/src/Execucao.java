import java.math.BigDecimal;

import com.av2.dados.Colaborador;
import com.av2.dados.Requisicao;
import com.av2.enumerados.TipoDepartamento;
import com.av2.enumerados.TipoRequisicao;
import com.av2.factory.ColaboradoresFactory;

public class Execucao {
	
	public static void main(String[] args) {
		Colaborador colaborador = null;
		ColaboradoresFactory factory = ColaboradoresFactory.getInstance();
		
		try {
			requisicaoesAcordoLegal(colaborador, factory);
			System.out.println();
			requisicaoesContratacoes(colaborador, factory);
			System.out.println();
			requisicaoesAquisicao(colaborador, factory);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void requisicaoesAcordoLegal(Colaborador colaborador, ColaboradoresFactory factory) throws InterruptedException {
		Requisicao requisicao = null;
		
		System.err.println("Requisi��o 1 de Acordo Legal para TI.\n");
		requisicao = new Requisicao(TipoRequisicao.ACORDO_LEGAL, TipoDepartamento.TI, new BigDecimal(5000));
		colaborador = factory.getColaborador(TipoDepartamento.TI, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
		Thread.sleep(2000);
		
		System.err.println("\nRequisi��o 2 de Acordo Legal para RH.\n");
		requisicao = new Requisicao(TipoRequisicao.ACORDO_LEGAL, TipoDepartamento.RH, new BigDecimal(20200));
		colaborador = factory.getColaborador(TipoDepartamento.RH, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
		Thread.sleep(2000);
		
		System.err.println("\nRequisi��o 3 de Acordo Legal para Juridico.\n");
		requisicao = new Requisicao(TipoRequisicao.ACORDO_LEGAL, TipoDepartamento.JURIDICO, new BigDecimal(452000));
		colaborador = factory.getColaborador(TipoDepartamento.JURIDICO, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
		Thread.sleep(2000);
		
		System.err.println("\nRequisi��o 4 de Acordo Legal para Opera��o.\n");
		requisicao = new Requisicao(TipoRequisicao.ACORDO_LEGAL, TipoDepartamento.OPERACAO, new BigDecimal(232000));
		colaborador = factory.getColaborador(TipoDepartamento.OPERACAO, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
	}
	
	public static void requisicaoesContratacoes(Colaborador colaborador, ColaboradoresFactory factory) throws InterruptedException {
		Requisicao requisicao = null;
		
		System.err.println("Requisi��o 1 de Contrata��o para Juridico.\n");
		requisicao = new Requisicao(TipoRequisicao.CONTRATACAO, TipoDepartamento.JURIDICO, new BigDecimal(18000));
		colaborador = factory.getColaborador(TipoDepartamento.JURIDICO, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
		Thread.sleep(2000);
		
		System.err.println("\nRequisi��o 2 de Contrata��o para Opera��o.\n");
		requisicao = new Requisicao(TipoRequisicao.CONTRATACAO, TipoDepartamento.OPERACAO, new BigDecimal(200000));
		colaborador = factory.getColaborador(TipoDepartamento.OPERACAO, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
		Thread.sleep(2000);
		
		System.err.println("\nRequisi��o 3 de Contrata��o para RH.\n");
		requisicao = new Requisicao(TipoRequisicao.CONTRATACAO, TipoDepartamento.RH, new BigDecimal(41000));
		colaborador = factory.getColaborador(TipoDepartamento.RH, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
		Thread.sleep(2000);
		
		System.err.println("\nRequisi��o 4 de Contrata��o para TI.\n");
		requisicao = new Requisicao(TipoRequisicao.CONTRATACAO, TipoDepartamento.TI, new BigDecimal(5000));
		colaborador = factory.getColaborador(TipoDepartamento.TI, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
		Thread.sleep(2000);
	}
	
	public static void requisicaoesAquisicao(Colaborador colaborador, ColaboradoresFactory factory) throws InterruptedException {
		Requisicao requisicao = null;
		
		System.err.println("Requisi��o 1 de Aquisi��o para Juridico.\n");
		requisicao = new Requisicao(TipoRequisicao.AQUISICAO, TipoDepartamento.JURIDICO, new BigDecimal(25000));
		colaborador = factory.getColaborador(TipoDepartamento.JURIDICO, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
		Thread.sleep(2000);
		
		System.err.println("\nRequisi��o 2 de Aquisi��o para Opera��o.\n");
		requisicao = new Requisicao(TipoRequisicao.AQUISICAO, TipoDepartamento.OPERACAO, new BigDecimal(400000));
		colaborador = factory.getColaborador(TipoDepartamento.OPERACAO, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
		Thread.sleep(2000);
		
		System.err.println("\nRequisi��o 3 de Aquisi��o para RH.\n");
		requisicao = new Requisicao(TipoRequisicao.AQUISICAO, TipoDepartamento.RH, new BigDecimal(38000));
		colaborador = factory.getColaborador(TipoDepartamento.RH, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
		
		Thread.sleep(2000);
		
		System.err.println("\nRequisi��o 4 de Aquisi��o para TI.\n");
		requisicao = new Requisicao(TipoRequisicao.AQUISICAO, TipoDepartamento.TI, new BigDecimal(5000));
		colaborador = factory.getColaborador(TipoDepartamento.TI, requisicao.getTipoRequisicao());
		colaborador.aprovarRequisicao(requisicao);
	}
}
