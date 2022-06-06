package controllers;

import java.util.ArrayList;

import javax.swing.JFrame;

import models.Orcamento;
import modelsdao.OrcamentoDAO;

public class OrcamentoProcess extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static ArrayList<Orcamento> orc = new ArrayList<>();
	private static OrcamentoDAO od = new OrcamentoDAO();

	public static void abrir() {
		orc = od.ler();
	}

	public static void salvar() {
		od.escrever(orc);
	}

	public static boolean compararProdutos() {
		boolean maisBarato = false;
		for (int i = 0; i < OrcamentoProcess.orc.size(); i++) {
			for (Orcamento o : OrcamentoProcess.orc) {
				if (maisBarato) {
					if (o.getPreco() < o.getPreco()) {
						o.setMaisBarato(maisBarato);
					}
				} else {
					maisBarato = true;
				}
			}
		}

		return maisBarato;
	}
}
