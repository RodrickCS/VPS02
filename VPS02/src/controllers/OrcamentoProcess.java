package controllers;

import java.util.ArrayList;

import models.Orcamento;
import modelsdao.OrcamentoDAO;

public class OrcamentoProcess {

	public static ArrayList<Orcamento> orc = new ArrayList<>();
	private static OrcamentoDAO od = new OrcamentoDAO();

	public static void abrir() {
		orc = od.ler();
	}

	public static void salvar() {
		od.escrever(orc);
	}

	public boolean compararProdutos() {
		boolean maisBarato = false;
		for (int i = 0; i < OrcamentoProcess.orc.size(); i++) {
			if (maisBarato == false) {
				
			}else {
				
			}
		}

		return maisBarato;
	}
}
