package models;

import java.util.Objects;

public class Orcamento {

	private int id;
	private String fornecedor;
	private String produto;
	private Double preco;
	private boolean maisBarato;

	public Orcamento() {

	}

	public Orcamento(String linha) {
		this.fornecedor = linha.split(";")[0];
		this.produto = linha.split(";")[1];
		this.preco = Double.parseDouble(linha.split(";")[2]);

	}

	public Orcamento(int id, String fornecedor, String produto, Double preco, boolean maisBarato) {

		this.id = id;
		this.fornecedor = fornecedor;
		this.produto = produto;
		this.preco = preco;
		this.maisBarato = maisBarato;
	}

	public Orcamento(int autoId, String fornecedor, String produto, Double preco) {
		this.id = autoId;
		this.fornecedor = fornecedor;
		this.produto = produto;
		this.preco = preco;

	}

	public Orcamento(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getId(String s) {
		return String.format("%d", id);
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Double getPreco() {
		return preco;
	}

	public Double getPreco(String s) {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public boolean isMaisBarato() {
		return maisBarato;
	}

	public void setMaisBarato(boolean maisBarato) {
		this.maisBarato = maisBarato;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orcamento other = (Orcamento) obj;
		return id == other.id;
	}

	public Double getSubTotal() {

		return null;
	}

	public String toCSV() {
		return id + ";" + fornecedor + ";" + produto + ";" + preco + ";" + maisBarato + ";" + "\r\n";
	}
}
