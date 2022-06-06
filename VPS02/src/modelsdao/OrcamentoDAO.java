package modelsdao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import models.Orcamento;

public class OrcamentoDAO {

	BufferedWriter bw;
	BufferedReader br;
	private String path = "C:\\Users\\DESENVOLVIMENTO\\Desktop\\VPS02\\VPS02\\dados\\Planilha.csv";
	
	
	public ArrayList<Orcamento> ler() {
		ArrayList<Orcamento> linhas = new ArrayList<>();
		Orcamento p;
		try {
			br = new BufferedReader(new FileReader(path));
			String linha = br.readLine();
			while (linha != null) {
				p = new Orcamento(linha);
				linhas.add(p);
				linha = br.readLine();
			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return linhas;

	}
	
	public void escrever(ArrayList<Orcamento> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(path));
			for (Orcamento p : linhas) {
				bw.write(p.toCSV());
			}
			bw.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
}
